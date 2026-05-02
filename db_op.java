import java.sql.* ;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class db_op {

    int patient_id = 0 ;
    int doctor_id = 0;

    static String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    static String user = "System";
    static String passwd = "2025";

    public static void insert_patient( String name , String lastname , String date ,  String address, String phone ){
        
        String sql = "INSERT INTO patient VALUES (seq_patient.NEXTVAL,?,?,?,?,?)";

        java.sql.Date sqlDate = java.sql.Date.valueOf(date);  

        try(

            //connecting to the database using try-with-resources to ensure proper resource management
            Connection conn = DriverManager.getConnection(url, user, passwd);
            
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //setting the parameters for the prepared statement
            pstmt.setString(1, lastname);
            pstmt.setString(2, name);
            pstmt.setDate(3, sqlDate);
            pstmt.setString(4, phone);
            pstmt.setString(5, address);

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Patient added successfully!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace(); // This prints the "why" in your console
        }
    }
    // same as insert_patient but for doctor table
    public static void insert_doctor( String name , String lastname , String specialty,  String phone ){


        String sql = "INSERT INTO doctor VALUES (seq_doctor.NEXTVAL,?,?,?,?)";
  

        try(
            Connection conn = DriverManager.getConnection(url, user, passwd);
            
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lastname);
            pstmt.setString(2, name);
            pstmt.setString(3, specialty);
            pstmt.setString(4, phone);
            

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Doctor added successfully!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace(); // This prints the "why" in your console
        }
    }

    public static String search_patient(String name, String lastname) {

        String sql = "SELECT * FROM patient WHERE firstname = ? AND lastname = ?";
        String result = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, passwd);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, name);
            pstmt.setString(2, lastname);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result =
                        "ID: " + rs.getInt(1) + "\n" +
                        "Name: " + rs.getString(3) + "\n" +
                        "Lastname: " + rs.getString(2) + "\n" +
                        "Birth Date: " + rs.getDate(4) + "\n" +
                        "Phone: " + rs.getString(5) + "\n" +
                        "Address: " + rs.getString(6);
            } else {
                result = "Patient not found";
            }

        } catch (SQLException e) {
            result = "Database error: " + e.getMessage();
            e.printStackTrace();
        }

        return result;
    }

    public static String search_doctor(String name, String lastname) {

        String sql = "SELECT * FROM doctor WHERE firstname = ? AND lastname = ?";
        String result = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, passwd);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, name);
            pstmt.setString(2, lastname);

            ResultSet rs = pstmt.executeQuery();
            
            boolean found = rs.next();

            System.out.println("Doctor search executed. Found: " + found);  

            if ( found) {
                result =
                        "ID: " + rs.getInt(1) + "\n" +
                        "Name: " + rs.getString(3) + "\n" +
                        "Lastname: " + rs.getString(2) + "\n" +
                        "Specialty: " + rs.getString(4) + "\n" +
                        "Phone: " + rs.getString(5) + "\n" ;
            } else {
                result = "Doctor not found";
            }

        } catch (SQLException e) {
            result = "Database error: " + e.getMessage();
            e.printStackTrace();
        }

        return result;
    }
    
    public static void delete_patient(int id) {
        String sql = "DELETE FROM patient WHERE num_patient = ?";
        try (
            Connection conn = DriverManager.getConnection(url, user, passwd);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Patient deleted." : "Patient not found.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void delete_doctor(int id) {
        String sql = "DELETE FROM doctor WHERE num_doctor = ?";
        try (
            Connection conn = DriverManager.getConnection(url, user, passwd);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Doctor deleted." : "Doctor not found.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void delete_Appointment(int id) {
        String sql = "DELETE FROM appointment WHERE num_appointment = ?";
        try (
            Connection conn = DriverManager.getConnection(url, user, passwd);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, rows > 0 ? "Appointment deleted." : "Appointment not found.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void update_patient(int id, String name, String lastname, String date, String phone, String address) {
        String sql = "UPDATE patient SET firstname=?, lastname=?, date_of_birth=?, phone=?, address=? WHERE num_patient=?";
        try (
            Connection conn = DriverManager.getConnection(url, user, passwd);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            pstmt.setDate(3, java.sql.Date.valueOf(date));
            pstmt.setString(4, phone);
            pstmt.setString(5, address);
            pstmt.setInt(6, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Patient updated." : "Patient not found.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void update_doctor(int id, String name, String lastname, String specialty, String phone) {
        String sql = "UPDATE doctor SET lastname=?, firstname=?, specialty=?, phone=? WHERE num_doctor=?";
        try (
            Connection conn = DriverManager.getConnection(url, user, passwd);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, lastname);
            pstmt.setString(2, name);
            pstmt.setString(3, specialty);
            pstmt.setString(4, phone);
            pstmt.setInt(5, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows);
            System.out.println(rows > 0 ? "Doctor updated." : "Doctor not found.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void loadData(JTable table) {
        String query = "SELECT num_doctor, lastname, firstname , specialty FROM doctor";

        // DefaultTableModel handles the column names and data rows
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Lastname", "Firstname", "Specialty"}, 0);

        try (Connection conn = DriverManager.getConnection(url, user, passwd);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Pull data from the row
                int id = rs.getInt("num_doctor");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String specialty = rs.getString("specialty");

                // Add the row to the model
                model.addRow(new Object[]{id, lastname, firstname, specialty});
            }

            // Set the model to the table
            table.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    public static void loadDataA(JTable table) {
        String query = "SELECT A.num_appointment, " +
               "P.firstname AS patient_fname, " + 
               "D.firstname AS doctor_fname, " + 
               "A.appt_date, A.appt_time, A.status " +
               "FROM appointment A, patient P, doctor D " +
               "WHERE A.num_patient = P.num_patient " +
               "AND A.num_doctor = D.num_doctor"; 

        // DefaultTableModel handles the column names and data rows
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "patient name", "doctor name", "date","time","status"}, 0);

        try (Connection conn = DriverManager.getConnection(url, user, passwd);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Pull data from the row
                int id = rs.getInt("num_appointment");
                String nameP  = rs.getString("patient_fname");
                String lastname = rs.getString("doctor_fname");
                String date = rs.getString("appt_date");
                String time = rs.getString("appt_time");
                String status = rs.getString("status");


                // Add the row to the model
                model.addRow(new Object[]{id, nameP, lastname, date, time, status});
            }

            // Set the model to the table
            table.setModel(model);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setPreferredWidth(150);
            table.getColumnModel().getColumn(3).setPreferredWidth(150);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    public static void book_attempt(int pat_id  ,int doc_id , String date , String time , String status ){
        String sql = "INSERT INTO appointment VALUES (seq_appointment.NEXTVAL, ?, ?, ? , ? ,?) ";

        try (
            Connection conn = DriverManager.getConnection(url, user, passwd);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setInt(1, pat_id);
            pstmt.setInt(2, doc_id);
            pstmt.setDate(3, java.sql.Date.valueOf(date));
            pstmt.setString(4, time);
            pstmt.setString(5, status);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Appointment booked successfully!");
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1: // ORA-00001 is the code for Unique Constraint violation
                    JOptionPane.showMessageDialog(null, "Error: This doctor is already booked at that time.", 
                                                  "Schedule Conflict", JOptionPane.ERROR_MESSAGE);
                    break;
                case 2291 : // ORA-02291 is the code for integrity constraint violation - parent key not found
                    JOptionPane.showMessageDialog(null, "Error: Invalid patient or doctor ID.", 
                                                  "Invalid ID", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
                                                  "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }





    

    

}
