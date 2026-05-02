import java.sql.* ;

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
            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            pstmt.setDate(3, sqlDate);
            pstmt.setString(4, phone);
            pstmt.setString(5, address);

            pstmt.executeUpdate();

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
                        "Name: " + rs.getString(2) + "\n" +
                        "Lastname: " + rs.getString(3) + "\n" +
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
                        "Name: " + rs.getString(2) + "\n" +
                        "Lastname: " + rs.getString(3) + "\n" +
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

} 

