/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.tiket;

import java.sql.*;

/**
 *
 * @author MasGer
 */
public class TampilTiket {
    private String id_tiket;
    private String nama;
    private String jenis_tiket;
    private int harga;
    private String tgl_booking;
    private String tgl_beli;
    private int jumlah_tiket;
    private int total;
    private String kode_tiket;
    Storage storage = Storage.getInstance();

    public String getId_tiket() {
        return id_tiket;
    }

    public void setId_tiket(String id_tiket) {
        this.id_tiket = id_tiket;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_tiket() {
        return jenis_tiket;
    }

    public void setJenis_tiket(String jenis_tiket) {
        this.jenis_tiket = jenis_tiket;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getTgl_booking() {
        return tgl_booking;
    }

    public void setTgl_booking(String tgl_booking) {
        this.tgl_booking = tgl_booking;
    }

    public String getTgl_beli() {
        return tgl_beli;
    }

    public void setTgl_beli(String tgl_beli) {
        this.tgl_beli = tgl_beli;
    }

    public int getJumlah_tiket() {
        return jumlah_tiket;
    }

    public void setJumlah_tiket(int jumlah_tiket) {
        this.jumlah_tiket = jumlah_tiket;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getKode_tiket() {
        return kode_tiket;
    }

    public void setKode_tiket(String kode_tiket) {
        this.kode_tiket = kode_tiket;
    }

    public void tampilTiket() {
        String sql = "SELECT * FROM tiket t INNER JOIN transaksi t2  ON t.id_tiket = t2.id_tiket WHERE t2.id_user =?";
        Connection con = null;
    
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            // Siapkan Objek Statement & ResultSet
            PreparedStatement stmt = null;
            ResultSet rs;

            // Gunakan Objek stmt untuk melakukan eksekusi query
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, storage.getUser_id());
            // Gunakan rs untuk menampung hasil query (select)
            rs = stmt.executeQuery();
            // Menampilkan data dari seluruh kolom pada tabel Buku dari objek rs
            while (rs.next()) {
                System.out.println("ID Tiket        : " + rs.getString("id_transaksi"));
                System.out.println("Nama Tiket      : " + rs.getString("nama_tiket"));
                System.out.println("Jenis Tiket     : " + rs.getString("jenis_tiket"));
                System.out.println("Harga           : " + rs.getInt("harga"));
                System.out.println("Tanggal Booking : " + rs.getString("tanggal_booking"));
                System.out.println("Tanggal Beli    : " + rs.getString("tanggal_pembelian"));
                System.out.println("Jumlah Tiket    : " + rs.getInt("jumlah_tiket"));
                System.out.println("Total Harga     : " + rs.getInt("total_bayar"));
                System.out.println("Kode Tiket      : " + rs.getString("kode_tiket"));
                System.out.println("");
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Koneksi gagal " + e.getMessage());
        }
    }
}
