import javax.swing.*;
import java.awt.*;

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

        JButton goP_Button = new JButton("Add a patient");
        goP_Button.addActionListener(e -> cardLayout.show(MainPanel, "Patient"));

        JButton goD_Button = new JButton("Add a Doctor");
        goD_Button.addActionListener(e -> cardLayout.show(MainPanel, "Doctor"));

        JButton goA_Button = new JButton("Add an Appointment");
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

        JButton Button_PName = new JButton("enter");
        JButton searchButton = new JButton("Search Patient");

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


        MainPanel.add(AppointmentPanel, "Appointment");

        JTextField TextFill_AName = new JTextField("Appointment name", 20);
        JButton Button_AName = new JButton("enter");

        AppointmentPanel.add(TextFill_AName);
        AppointmentPanel.add(Button_AName);
    }
}