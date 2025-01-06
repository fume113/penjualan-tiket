package penjualan.tiket;
import java.sql.*;

public class ViewProfile {
    Storage storage = Storage.getInstance();
    public void viewProfile(){
        String getProfile = "SELECT * FROM user WHERE id_user = '" + storage.getUser_id() + "'";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getProfile);
            while (rs.next()) {
                System.out.println("Nama : " + rs.getString("nama"));
                System.out.println("Username : " + rs.getString("username"));
                System.out.println("Password : " + rs.getString("password"));
                System.out.println("Role : " + rs.getString("role"));
                System.out.println("Alamat : " + rs.getString("alamat"));
                System.out.println("No Telp : " + rs.getString("no_hp"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
