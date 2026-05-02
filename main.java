import javax.swing.*;
import java.awt.*;

public class main {

    public static void main(String[] args) {

        JFrame window = new JFrame("app");

        CardLayout cardLayout = new CardLayout();
        JPanel MainPanel = new JPanel(cardLayout);

        JPanel PatientPanel      = new JPanel();
        JPanel DoctorPanel       = new JPanel();
        JPanel AppointmentPanel  = new JPanel();
        JPanel Home              = new JPanel();
        JPanel navBar            = new JPanel();

        window.setLayout(new BorderLayout());
        window.add(navBar, BorderLayout.NORTH);
        window.add(MainPanel, BorderLayout.CENTER);
        window.setSize(500, 500);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        navBar.setBackground(Color.LIGHT_GRAY);
        JButton homeButton = new JButton("🏠 Back to Home");
        homeButton.addActionListener(e -> cardLayout.show(MainPanel, "Home"));
        navBar.add(homeButton);

        MainPanel.add(Home, "Home");

        JButton goP_Button = new JButton("Patient");
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

        JScrollPane patientScroll = new JScrollPane(PatientData);
        PatientPanel.setLayout(new BorderLayout());
        PatientPanel.add(patientScroll, BorderLayout.CENTER);

        JTextField TextFill_PName   = new JTextField("patient name", 20);
        JTextField TextFill_PFName  = new JTextField("patient family name", 20);
        JTextField TextFill_Pphone  = new JTextField("patient phone number", 20);
        JTextField TextFill_Padress = new JTextField("patient address", 20);
        JTextField TextFill_Date    = new JTextField("2000-01-01", 20);

        JTextField TextFill_PID = new JTextField("patient ID", 20);

        JButton Button_Insert   = new JButton("Insert Patient");
        JButton searchButton    = new JButton("Search Patient");
        JButton deleteButtonP   = new JButton("Delete Patient");
        JButton updateButtonP   = new JButton("Update Patient");

        Button_Insert.addActionListener(e -> {
            db_op.insert_patient(
                    TextFill_PName.getText(),
                    TextFill_PFName.getText(),
                    TextFill_Date.getText(),
                    TextFill_Padress.getText(),
                    TextFill_Pphone.getText()
            );
            JOptionPane.showMessageDialog(null, "Patient inserted.");
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

        PatientData.add(new JLabel("── Insert / Search ──────────────"));
        PatientData.add(Box.createVerticalStrut(5));
        PatientData.add(TextFill_PName);
        PatientData.add(Box.createVerticalStrut(8));
        PatientData.add(TextFill_PFName);
        PatientData.add(Box.createVerticalStrut(8));
        PatientData.add(TextFill_Pphone);
        PatientData.add(Box.createVerticalStrut(8));
        PatientData.add(TextFill_Padress);
        PatientData.add(Box.createVerticalStrut(8));
        PatientData.add(TextFill_Date);
        PatientData.add(Box.createVerticalStrut(8));
        PatientData.add(Button_Insert);
        PatientData.add(Box.createVerticalStrut(8));
        PatientData.add(searchButton);
        PatientData.add(Box.createVerticalStrut(15));
        PatientData.add(new JLabel("── Delete / Update (requires ID) ─"));
        PatientData.add(Box.createVerticalStrut(5));
        PatientData.add(TextFill_PID);
        PatientData.add(Box.createVerticalStrut(8));
        PatientData.add(deleteButtonP);
        PatientData.add(Box.createVerticalStrut(8));
        PatientData.add(updateButtonP);


        MainPanel.add(DoctorPanel, "Doctor");

        JPanel DoctorData = new JPanel();
        DoctorData.setLayout(new BoxLayout(DoctorData, BoxLayout.Y_AXIS));
        DoctorData.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane doctorScroll = new JScrollPane(DoctorData);
        DoctorPanel.setLayout(new BorderLayout());
        DoctorPanel.add(doctorScroll, BorderLayout.CENTER);

        JTextField TextFill_DName   = new JTextField("Doctor name", 20);
        JTextField TextFill_DFName  = new JTextField("Doctor family name", 20);
        JTextField TextFill_Dphone  = new JTextField("Doctor phone number", 20);
        JTextField TextFill_Dadress = new JTextField("Doctor specialty", 20);


        JTextField TextFill_DID = new JTextField("doctor ID", 20);

        JButton Button_DInsert  = new JButton("Insert Doctor");
        JButton searchButtonD   = new JButton("Search Doctor");
        JButton deleteButtonD   = new JButton("Delete Doctor");
        JButton updateButtonD   = new JButton("Update Doctor");


        Button_DInsert.addActionListener(e -> {
            db_op.insert_doctor(
                    TextFill_DName.getText(),
                    TextFill_DFName.getText(),
                    TextFill_Dadress.getText(),
                    TextFill_Dphone.getText()
            );
            JOptionPane.showMessageDialog(null, "Doctor inserted.");
        });

        searchButtonD.addActionListener(e -> {
            String result = db_op.search_doctor(
                    TextFill_DName.getText(),
                    TextFill_DFName.getText()
            );
            JOptionPane.showMessageDialog(null, result);
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

        DoctorData.add(new JLabel("── Insert / Search ──────────────"));
        DoctorData.add(Box.createVerticalStrut(5));
        DoctorData.add(TextFill_DName);
        DoctorData.add(Box.createVerticalStrut(8));
        DoctorData.add(TextFill_DFName);
        DoctorData.add(Box.createVerticalStrut(8));
        DoctorData.add(TextFill_Dphone);
        DoctorData.add(Box.createVerticalStrut(8));
        DoctorData.add(TextFill_Dadress);
        DoctorData.add(Box.createVerticalStrut(8));
        DoctorData.add(Button_DInsert);
        DoctorData.add(Box.createVerticalStrut(8));
        DoctorData.add(searchButtonD);
        DoctorData.add(Box.createVerticalStrut(15));
        DoctorData.add(new JLabel("── Delete / Update (requires ID) ─"));
        DoctorData.add(Box.createVerticalStrut(5));
        DoctorData.add(TextFill_DID);
        DoctorData.add(Box.createVerticalStrut(8));
        DoctorData.add(deleteButtonD);
        DoctorData.add(Box.createVerticalStrut(8));
        DoctorData.add(updateButtonD);


        MainPanel.add(AppointmentPanel, "Appointment");

        JTextField TextFill_AName = new JTextField("Appointment name", 20);
        JButton Button_AName = new JButton("enter");

        AppointmentPanel.add(TextFill_AName);
        AppointmentPanel.add(Button_AName);
    }
}
