
import javax.swing.*;
import java.awt.*;

public static void main(){


    JFrame window = new JFrame("app");
    CardLayout cardLayout = new CardLayout();


    // main windw + sub panels

    JPanel MainPanel = new JPanel(cardLayout);
    JPanel PatientPanel = new JPanel();
    JPanel DoctorPanel = new JPanel();
    JPanel AppointmentPanel = new JPanel();
    JPanel Home = new JPanel();
    JPanel navBar = new JPanel();


    window.setLayout(new BorderLayout());
    window.add(navBar, BorderLayout.NORTH);   
    window.add(MainPanel, BorderLayout.CENTER);
    window.setSize(300,100);
    window.setLocation(100, 100);
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setVisible(true);

    // lbara li mlfo9 bach twli
    navBar.setBackground(Color.LIGHT_GRAY);
    JButton homeButton = new JButton("🏠 Back to Home");
    homeButton.addActionListener(e -> cardLayout.show(MainPanel, "Home"));
    navBar.add(homeButton);

    // lpaja lwla w l9fali li fiha
    MainPanel.add(Home , "Home");

        JButton goP_Button = new JButton("Add a patien");
        goP_Button.addActionListener( e -> { cardLayout.show(MainPanel, "Patien");});

        JButton goD_Button = new JButton("Add a Doctor");
        goD_Button.addActionListener( e -> { cardLayout.show(MainPanel, "Doctor");});

        JButton goA_Button = new JButton("Add a Appointment");
        goA_Button.addActionListener( e -> { cardLayout.show(MainPanel, "Appointment");});

        Home.add(goP_Button);
        Home.add(goD_Button);
        Home.add(goA_Button);
    

    // declaration dyal lbara li fiha lbuttons dyal navigation
    JButton Home_Button = new JButton("Home");
    Home_Button.addActionListener( e -> { cardLayout.show(MainPanel, "Home");});
        
    // ta3 les patients 
    MainPanel.add(PatientPanel , "Patien");

            //el cadr
            JPanel PatientData = new JPanel();
            PatientData.setLayout(new BoxLayout(PatientData,BoxLayout.Y_AXIS));
            PatientData.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            PatientPanel.add(PatientData);

            //lkhrayb li dakhl lpaja
            JTextField TextFill_PName = new JTextField(  "patient name" , 20);
            JTextField TextFill_PFName = new JTextField( "patient family name", 20);
            JTextField TextFill_Pphone = new JTextField( "patient phone number" , 20);
            JTextField TextFill_Padress = new JTextField("patient adress" , 20);
            JFormattedTextField TextFill_Date = new JFormattedTextField("##-##-####"); 

            JButton Button_PName = new JButton("enter" );

            //L9FLA li dir insert f database
            Button_PName.addActionListener(e -> {
                db_op.insert_patient(TextFill_PName.getText(),TextFill_PFName.getText(), TextFill_Date.getText(),TextFill_Padress.getText(),TextFill_Pphone.getText());
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
            

            
    // ta3 les doctors
    MainPanel.add(DoctorPanel , "Doctor");

        
            //el cadr
            JPanel DoctorData = new JPanel();
            DoctorData.setLayout(new BoxLayout(DoctorData,BoxLayout.Y_AXIS));
            DoctorData.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            DoctorPanel.add(DoctorData);

            //lkhrayb li dakhl lpaja
            JTextField TextFill_DName = new JTextField(  "Doctor name" , 20);
            JTextField TextFill_DFName = new JTextField(  "Doctor family name", 20);
            JTextField TextFill_Dphone = new JTextField( "Doctor phone number" ,  20);
            JTextField TextFill_Dspecialty = new JTextField("Doctor specialty" , 20);
            JButton Button_DName = new JButton("enter" );


            Button_DName.addActionListener(e -> {
                db_op.insert_doctor(TextFill_DName.getText(),TextFill_DFName.getText(),TextFill_Dspecialty.getText(),TextFill_Pphone.getText());
            });
            
            //7t lkhrayb fl paja
            DoctorData.add(TextFill_DName);
            DoctorData.add(Box.createVerticalStrut(10));
            DoctorData.add(TextFill_DFName);
            DoctorData.add(Box.createVerticalStrut(10));
            DoctorData.add(TextFill_Dphone);
            DoctorData.add(Box.createVerticalStrut(10));
            DoctorData.add(TextFill_Dspecialty);
            DoctorData.add(Box.createVerticalStrut(10));
            DoctorData.add(Button_DName);
            
    // ta3 les appointments
    MainPanel.add(AppointmentPanel , "Appointment");

        
            JTextField TextFill_AName = new JTextField("Appointment name", 20);
            JButton Button_AName = new JButton("enter" );

            AppointmentPanel.add(TextFill_AName);
            AppointmentPanel.add(Button_AName);
            AppointmentPanel.setSize(100,100);
            AppointmentPanel.add(Home_Button);
            


    
    
}
