package penjualan.tiket;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Rijal
 */
public class VerifikasiTiket {

    private String kodetiket, tanggalbooking, tanggalpembelian, status;
    Storage storage = Storage.getInstance();

    public String getKodeTiket() {
        return kodetiket;
    }

    public void setKodeTiket(String kodetiket) {
        this.kodetiket = kodetiket;
    }

    public String getTanggalBooking() {
        return tanggalbooking;
    }

    public void setTanggalBooking(String tanggalbooking) {
        this.tanggalbooking = tanggalbooking;
    }

    public String getTanggalPembelian() {
        return tanggalpembelian;
    }

    public void setTanggalPembelian(String tanggalpembelian) {
        this.tanggalpembelian = tanggalpembelian;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void viewVerifikasi() {
        Scanner input = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.print("Masukan Kode Tiket: ");
        setKodeTiket(input.nextLine());
        System.out.println("==========================================================");
        String hari_ini = LocalDate.now().toString();
        String queryCariTransaksi = "SELECT * FROM transaksi WHERE kode_tiket = '" + getKodeTiket() + "' AND tanggal_booking = '" + hari_ini + "' AND status = 'UNUSED'";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryCariTransaksi);
            if (rs.next()) {
                setTanggalBooking(rs.getString("tanggal_booking"));
                setTanggalPembelian(rs.getString("tanggal_pembelian"));
                setStatus(rs.getString("status"));
                System.out.println("==========================================================");
                System.out.println("Kode Tiket: " + getKodeTiket());
                System.out.println("Tanggal Booking: " + getTanggalBooking());
                System.out.println("Tanggal Pembelian: " + getTanggalPembelian());
                System.out.println("Status: " + getStatus());
                System.out.print("Apakah anda yakin ingin memverifikasi tiket ini? (y/n): ");
                String jawaban = input.nextLine();
                if (jawaban.equals("y")) {
                    String queryUpdateTransaksi = "UPDATE transaksi SET status = 'USED' WHERE kode_tiket = '" + getKodeTiket() + "' AND tanggal_booking = '" + hari_ini + "' AND status = 'UNUSED'";
                    try {
                        Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
                        Statement stmt2 = con2.createStatement();
                        stmt2.executeUpdate(queryUpdateTransaksi);
                        System.out.println("Tiket berhasil diverifikasi");
                    } catch (SQLException e) {
                        System.out.println("Tiket gagal diverifikasi");
                    }
                } else {
                    System.out.println("Tiket tidak diverifikasi");
                }
            } else {
                System.out.println("==========================================================");
                System.out.println("Kode Tiket tidak ditemukan atau sudah digunakan");
                System.out.println("==========================================================");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
