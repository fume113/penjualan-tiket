package penjualan.tiket;
import java.sql.*;
import java.util.Scanner;

public class BuatVoucher {
    private String kode_voucher;
    private int nominal;
    Storage storage = Storage.getInstance();
    public String getKode_voucher() {
        return kode_voucher;
    }
    public int getNominal() {
        return nominal;
    }
    public void setKode_voucher(String kode_voucher) {
        this.kode_voucher = kode_voucher;
    }
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public void viewBuatVoucher(){
        Scanner input = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.print("Masukan nominal voucher : ");
        nominal = input.nextInt();
        System.out.println("==========================================================");
        String queryInputVoucher = "INSERT INTO voucher(id_user,kode_voucher,nominal,status) VALUES(?,?,?,?)";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
            PreparedStatement pst = con.prepareStatement(queryInputVoucher);
            pst.setInt(1, storage.getUser_id());  
            pst.setString(2, RandomString.getAlphaNumericString(8));
            pst.setInt(3, nominal);
            pst.setString(4, "UNUSED");      
            pst.executeUpdate();
            System.out.println("Voucher berhasil ditambahkan");
        } catch (SQLException e) {
            System.out.println("Voucher gagal ditambahkan");
            e.printStackTrace();

        }
    }
}
