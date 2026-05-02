import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class main {

    public static void main(String[] args) {

        JFrame window = new JFrame("app");

        CardLayout cardLayout = new CardLayout();
        JPanel MainPanel = new JPanel(cardLayout);

        JPanel PatientPanel = new JPanel();
        JPanel DoctorPanel = new JPanel();
        JPanel AppointmentPanel = new JPanel();
        JPanel Home = new JPanel();
        JPanel navBar = new JPanel();

        window.setLayout(new BorderLayout());
        window.add(navBar, BorderLayout.NORTH);
        window.add(MainPanel, BorderLayout.CENTER);
        window.setSize(500, 400);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        navBar.setBackground(Color.LIGHT_GRAY);
        JButton homeButton = new JButton("🏠 Back to Home");
        homeButton.addActionListener(e -> cardLayout.show(MainPanel, "Home"));
        navBar.add(homeButton);

        MainPanel.add(Home, "Home");

        JButton goP_Button = new JButton("patient");
        goP_Button.addActionListener(e -> cardLayout.show(MainPanel, "Patient"));

        JButton goD_Button = new JButton("Doctor");
        goD_Button.addActionListener(e -> cardLayout.show(MainPanel, "Doctor"));

        JButton goA_Button = new JButton("Appointment");
        goA_Button.addActionListener(e -> cardLayout.show(MainPanel, "Appointment"));

        Home.add(goP_Button);
        Home.add(goD_Button);
        Home.add(goA_Button);


        MainPanel.add(PatientPanel, "Patient");

        JPanel PatientData = new JPanel();
        PatientData.setLayout(new BoxLayout(PatientData, BoxLayout.Y_AXIS));
        PatientData.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        PatientPanel.add(PatientData);

        JTextField TextFill_PName = new JTextField("patient name", 20);
        JTextField TextFill_PFName = new JTextField("patient family name", 20);
        JTextField TextFill_Pphone = new JTextField("patient phone number", 20);
        JTextField TextFill_Padress = new JTextField("patient address", 20);
        JTextField TextFill_Date = new JTextField("2000-01-01", 20);

        JTextField TextFill_PID = new JTextField("patient ID", 20);

        JButton Button_PName = new JButton("enter");
        JButton searchButton = new JButton("Search Patient");
        JButton deleteButtonP   = new JButton("Delete Patient");
        JButton updateButtonP   = new JButton("Update Patient");

        Button_PName.addActionListener(e -> {
            db_op.insert_patient(
                    TextFill_PName.getText(),
                    TextFill_PFName.getText(),
                    TextFill_Date.getText(),
                    TextFill_Padress.getText(),
                    TextFill_Pphone.getText()
            );
        });

        searchButton.addActionListener(e -> {
            String result = db_op.search_patient(
                    TextFill_PName.getText(),
                    TextFill_PFName.getText()
            );

            JOptionPane.showMessageDialog(null, result);
        });

        deleteButtonP.addActionListener(e -> {
            try {
                int id = Integer.parseInt(TextFill_PID.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Delete patient with ID " + id + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    db_op.delete_patient(id);
                    JOptionPane.showMessageDialog(null, "Patient deleted.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
            }
        });

        updateButtonP.addActionListener(e -> {
            try {
                int id = Integer.parseInt(TextFill_PID.getText().trim());
                db_op.update_patient(
                        id,
                        TextFill_PName.getText(),
                        TextFill_PFName.getText(),
                        TextFill_Date.getText(),
                        TextFill_Pphone.getText(),
                        TextFill_Padress.getText()
                );
                JOptionPane.showMessageDialog(null, "Patient updated.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
            }
        });

        PatientData.add(TextFill_PName);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(TextFill_PFName);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(TextFill_Pphone);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(TextFill_Padress);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(TextFill_Date);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(Button_PName);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(searchButton);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(TextFill_PID);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(deleteButtonP);
        PatientData.add(Box.createVerticalStrut(10));
        PatientData.add(updateButtonP);


        MainPanel.add(DoctorPanel, "Doctor");

        JPanel DoctorData = new JPanel();
        DoctorData.setLayout(new BoxLayout(DoctorData, BoxLayout.Y_AXIS));
        DoctorData.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        DoctorPanel.add(DoctorData);

        JTextField TextFill_DName = new JTextField("Doctor name", 20);
        JTextField TextFill_DFName = new JTextField("Doctor family name", 20);
        JTextField TextFill_Dphone = new JTextField("Doctor phone number", 20);
        JTextField TextFill_Dadress = new JTextField("Doctor specialty", 20);
        JButton Button_DName = new JButton("enter");
        JButton searchButtonD = new JButton("Search Doctor");
        JButton displayAllDoctors = new JButton("Display all doctors");
        JTable doctorTable = new JTable();
        JButton deleteButtonD   = new JButton("Delete Doctor");
        JButton updateButtonD   = new JButton("Update Doctor");
        JTextField TextFill_DID = new JTextField("doctor ID", 20);



        Button_DName.addActionListener(e -> {
            db_op.insert_doctor(
                    TextFill_DName.getText(),
                    TextFill_DFName.getText(),
                    TextFill_Dadress.getText(),
                    TextFill_Dphone.getText()
            );
        });


        searchButtonD.addActionListener(e -> {
            String result = db_op.search_doctor(
                    TextFill_DName.getText(),
                    TextFill_DFName.getText()
            );

            JOptionPane.showMessageDialog(null, result);
        });

        displayAllDoctors.addActionListener(e -> {
            db_op.loadData(doctorTable);
            JOptionPane.showMessageDialog(null, new JScrollPane(doctorTable), "All Doctors", JOptionPane.INFORMATION_MESSAGE);
        });

        deleteButtonD.addActionListener(e -> {
            try {
                int id = Integer.parseInt(TextFill_DID.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Delete doctor with ID " + id + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    db_op.delete_doctor(id);
                    JOptionPane.showMessageDialog(null, "Doctor deleted.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
            }
        });

        updateButtonD.addActionListener(e -> {
            try {
                int id = Integer.parseInt(TextFill_DID.getText().trim());
                db_op.update_doctor(
                        id,
                        TextFill_DName.getText(),
                        TextFill_DFName.getText(),
                        TextFill_Dadress.getText(),
                        TextFill_Dphone.getText()
                );
                JOptionPane.showMessageDialog(null, "Doctor updated.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
            }
        });            


        DoctorData.add(TextFill_DName);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(TextFill_DFName);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(TextFill_Dphone);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(TextFill_Dadress);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(Button_DName);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(searchButtonD);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(displayAllDoctors);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(TextFill_DID);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(deleteButtonD);
        DoctorData.add(Box.createVerticalStrut(10));
        DoctorData.add(updateButtonD);


        MainPanel.add(AppointmentPanel, "Appointment");

        JTextField TextFill_PAName = new JTextField("patient id", 20);
        JTextField TextFill_DAName = new JTextField("doctor id", 20);
        JTextField TextFill_ADate = new JTextField("2000-01-01", 20);
        JTextField TextFill_Time = new JTextField("00:00", 20);
        JTextField TextFill_Status = new JTextField("pending", 20);

        JButton Button_book = new JButton("enter");
        JButton displayAppointments = new JButton("Display Appointments");
        JButton deleteButtonA   = new JButton("Delete Appointment");
        JTextField TextFill_AID = new JTextField("appointment ID", 20);


        displayAppointments.addActionListener(e -> {
            JTable appointmentTable = new JTable();
            db_op.loadDataA(appointmentTable);
            JOptionPane.showMessageDialog(null, new JScrollPane(appointmentTable), "All Appointments", JOptionPane.INFORMATION_MESSAGE);
        });

        Button_book.addActionListener(e -> {
            try {
                int patientId = Integer.parseInt(TextFill_PAName.getText().trim());
                int doctorId = Integer.parseInt(TextFill_DAName.getText().trim());
                String date = TextFill_Date.getText().trim();
                String time = TextFill_Time.getText().trim();
                String status = TextFill_Status.getText().trim();

                db_op.book_attempt(patientId, doctorId, date, time, status);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric IDs for patient and doctor.");
            }

        });

        deleteButtonA.addActionListener(e -> {
            try {
                int id = Integer.parseInt(TextFill_AID.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Delete appointment with ID " + id + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    db_op.delete_Appointment(id);
                    JOptionPane.showMessageDialog(null, "Appointment deleted.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.");
            }
        });
                

        AppointmentPanel.add(TextFill_PAName);
        AppointmentPanel.add(TextFill_DAName);
        AppointmentPanel.add(TextFill_Date);
        AppointmentPanel.add(TextFill_Time);
        AppointmentPanel.add(TextFill_Status);
        AppointmentPanel.add(Button_book);
        AppointmentPanel.add(Box.createVerticalStrut(10));
        AppointmentPanel.add(displayAppointments);
        AppointmentPanel.add(Box.createVerticalStrut(10));
        AppointmentPanel.add(TextFill_AID);
        AppointmentPanel.add(Box.createVerticalStrut(10));
        AppointmentPanel.add(deleteButtonA);
    }
}