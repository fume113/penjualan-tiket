/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.tiket;
import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author muham
 */
public class Belitiket {
    private String jenis_tiket, nama_tiket, tanggal_booking, tanggal_pembelian;
    private int jumlah_tiket, total, harga, kode_tiket;
    LocalDate TanggalSekarang = LocalDate.now();
    Connection con = null;
    PreparedStatement pst = null;
    Storage storage = Storage.getInstance();
    /**
     * @return the nama_tiket
     */
    public String getNama_tiket() {
        return nama_tiket;
    }

    /**
     * @param nama_tiket the nama_tiket to set
     */
    public void setNama_tiket(String nama_tiket) {
        this.nama_tiket = nama_tiket;
    }

    public String getJenistiket() {
        return jenis_tiket;
    }

    public void setJenis_tiket(String jenis_tiket) {
        if ("dewasa".equals(jenis_tiket)) {
            setHarga(30000);
        } else if ("anakanak".equals(jenis_tiket)) {
            setHarga(15000);
        }
        this.jenis_tiket = jenis_tiket;
    }

    public String getTanggal_booking() {
        return tanggal_booking;
    }

    public void setTanggal_booking(String tanggal_booking) {

        this.tanggal_booking = tanggal_booking;
    }

    public String getTanggal_pembelian() {
        return tanggal_pembelian;
    }

    public void setTanggal_pembelian(String tanggal_pembelian) {

        this.tanggal_pembelian = tanggal_pembelian;
    }

    /**
     * @return the jumlahtiket
     */
    public int getJumlah_tiket() {
        return jumlah_tiket;
    }

    public void setJumlah_tiket(int jumlah_tiket) {
        this.jumlah_tiket = jumlah_tiket;
    }

    /**
     * @return the totharga
     */
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {

        jenis_tiket = "dewasa";
        jenis_tiket = "anakanak";
        total = jumlah_tiket * harga;

        this.total = total;
    }

    /**
     * @return the harga
     */
    public int getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(int harga) {
        this.harga = harga;
    }

    /**
     * @return the kode_tiket
     */
    public int getKode_tiket() {
        return kode_tiket;
    }

    /**
     * @param kode_tiket the kode_tiket to set
     */
    public void setKode_tiket(int kode_tiket) {
        this.kode_tiket = kode_tiket;
    }

    public void belitiket() throws SQLException {
        String query = "SELECT * FROM tiket WHERE jenis_tiket=? AND jumlah_tiket=?";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
        } catch (SQLException e) {
            // TODO Auto-generated catch block

        }
        pst = con.prepareStatement(query);
        pst.setString(1, jenis_tiket);
        pst.setInt(2, jumlah_tiket);
        pst.setInt(2, total);

    }

    public void viewbelitiket() throws SQLException {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String query = "SELECT * FROM tiket";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
        } catch (SQLException e) {
            e.getStackTrace();
        }
        PreparedStatement stmt = null, stmt2 = null, stmt3 = null;
        ResultSet rs;

        // Gunakan Objek stmt untuk melakukan eksekusi query
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery();
        // Menampilkan data dari seluruh kolom pada tabel Buku dari objek rs
        String leftAlignFormat = "| %-4d | %-19s | %-11s | %-11d |%n";
        System.out.format("+------+---------------------+-------------+-------------+%n");
        System.out.format("| ID   | Nama Tiket          | Jenis Tiket | Harga Tiket |%n");
        System.out.format("+------+---------------------+-------------+-------------+%n");
        while (rs.next()) {
            System.out.format(leftAlignFormat, rs.getInt("id_tiket"), rs.getString("nama_tiket"),
                    rs.getString("jenis_tiket"), rs.getInt("harga"));
        }
        System.out.format("+------+---------------------+-------------+-------------+%n");
        stmt.close();
        System.out.print("Pilih tiket : ");
        int idTiket = sc.nextInt();
        System.out.print("Jumlah tiket : ");
        int jumlahTiket = sc.nextInt();
        System.out.print("Tanggal booking : ");
        String tanggalBooking = sc.next();
        System.out.println("===========================================================");
        String queryPilihTIket = "SELECT * FROM tiket WHERE id_tiket=?";
        stmt2 = con.prepareStatement(queryPilihTIket);
        stmt2.setInt(1, idTiket);
        rs = stmt2.executeQuery();
        int totalHarga = 0;
        if (rs.next()) {
            totalHarga = rs.getInt("harga") * jumlahTiket;
            System.out.println("Nama tiket : " + rs.getString("nama_tiket"));
            System.out.println("Jenis tiket : " + rs.getString("jenis_tiket"));
            System.out.println("Harga tiket : " + rs.getInt("harga"));
            System.out.println("Jumlah tiket : " + jumlahTiket);
            System.out.println("Total harga : " + totalHarga);
            System.out.println("Tanggal booking : " + tanggalBooking);
            System.out.println("=========================================================");
        }
        stmt2.close();
        System.out.print("Apakah anda ingin membeli tiket ? (y/n) : ");
        String jawab = sc.next();
        if (jawab.equals("y")) {
            String querygetsaldo = "SELECT * FROM saldo WHERE id_user=?";
            stmt3 = con.prepareStatement(querygetsaldo);
            stmt3.setInt(1, storage.getUser_id());
            rs = stmt3.executeQuery();
            while (rs.next()) {
                int saldo = rs.getInt("saldo");
                if (saldo >= totalHarga) {
                    
                    String queryUpdateSaldo = "UPDATE saldo SET saldo=? WHERE id_user=?";
                    PreparedStatement stmtUpdateSaldo = con.prepareStatement(queryUpdateSaldo);
                    stmtUpdateSaldo.setInt(1, saldo - totalHarga);
                    stmtUpdateSaldo.setInt(2, storage.getUser_id());
                    stmtUpdateSaldo.executeUpdate();
                    stmtUpdateSaldo.close();
                    String queryBeliTiket = "INSERT INTO transaksi (id_tiket, id_user ,jumlah_tiket, total_bayar, metode_pembayaran, kode_tiket ,tanggal_booking, tanggal_pembelian, status) VALUES (?,?,?,?,?,?,?,?,?)";
                    PreparedStatement stmt4 = con.prepareStatement(queryBeliTiket);
                    LocalDate tanggalsekarang = LocalDate.now();
                    stmt4.setInt(1, idTiket);
                    stmt4.setInt(2, storage.getUser_id());
                    stmt4.setInt(3, jumlahTiket);
                    stmt4.setInt(4, totalHarga);
                    stmt4.setString(5, "saldo");
                    stmt4.setString(6, RandomString.getAlphaNumericString(10));
                    stmt4.setString(7, tanggalBooking);
                    stmt4.setString(8, tanggalsekarang.toString());
                    stmt4.setString(9, "UNUSED");
                    stmt4.executeUpdate();
                    stmt4.close();
                    System.out.println("Tiket berhasil dibeli");
                    System.out.println("Pembelian tiket berhasil");
                } else {
                    System.out.println("Saldo anda tidak mencukupi");
                }
            }

        } else {
            System.out.println("Tiket tidak dibeli");
        }

    }

}
