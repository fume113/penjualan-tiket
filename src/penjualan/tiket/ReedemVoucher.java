package penjualan.tiket;
import java.sql.*;
import java.util.Scanner;

public class ReedemVoucher {
    private String kode_voucher;
    Storage storage = Storage.getInstance();
    public String getKode_voucher() {
        return kode_voucher;
    }
    public void setKode_voucher(String kode_voucher) {
        this.kode_voucher = kode_voucher;
    }

    public void viewReedem(){
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan kode voucher: ");
        kode_voucher = input.nextLine();
        String query = "SELECT * FROM voucher WHERE kode_voucher = ? AND status = 'unused'";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, kode_voucher);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Kode Voucher: " + rs.getString("kode_voucher"));
                System.out.println("Nominal: " + rs.getString("nominal"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.print("Yakin ingin menggunakan voucher ini? (y/n) ");
                String jawab = input.nextLine();
                if (jawab.equals("y")) {
                    String query2 = "UPDATE voucher SET status = 'used' WHERE kode_voucher = ?";
                    PreparedStatement pst2 = con.prepareStatement(query2);
                    pst2.setString(1, kode_voucher);
                    pst2.executeUpdate();
                    String getUserSaldo = "SELECT * FROM saldo WHERE id_user = ?";
                    PreparedStatement pst3 = con.prepareStatement(getUserSaldo);
                    pst3.setInt(1, storage.getUser_id());
                    ResultSet rs2 = pst3.executeQuery();
                    if (rs2.next()) {
                        int saldo = rs2.getInt("saldo");
                        int nominal = rs.getInt("nominal");
                        int total = saldo + nominal;
                        String query3 = "UPDATE saldo SET saldo = ? WHERE id_user = ?";
                        PreparedStatement pst4 = con.prepareStatement(query3);
                        pst4.setInt(1, total);
                        pst4.setInt(2, storage.getUser_id());
                        pst4.executeUpdate();
                        System.out.println("Voucher berhasil digunakan");
                        System.out.println("Saldo anda sekarang: " + total);
                    }
                } else {
                    System.out.println("Voucher tidak digunakan");
                }
            } else {
                System.out.println("Kode Voucher tidak ditemukan");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
