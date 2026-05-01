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

            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            pstmt.setString(3, specialty);
            pstmt.setString(4, phone);
            

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace(); // This prints the "why" in your console
        }
    }

} 

