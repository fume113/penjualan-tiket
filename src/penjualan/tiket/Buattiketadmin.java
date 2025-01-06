/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.tiket;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author muham
 */
public class Buattiketadmin {
    private String nama_tiket, jenis_tiket;
    private int harga;
    public void setHarga(int harga) {
        this.harga = harga;
    }
    public void setJenis_tiket(String jenis_tiket) {
        this.jenis_tiket = jenis_tiket;
    }
    public void setNama_tiket(String nama_tiket) {
        this.nama_tiket = nama_tiket;
    }
    public int getHarga() {
        return harga;
    }
    public String getJenis_tiket() {
        return jenis_tiket;
    }
    public String getNama_tiket() {
        return nama_tiket;
    }
    public void viewbuattiket() {
        Scanner input = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.print("Masukan nama tiket : ");
        nama_tiket = input.nextLine();
        System.out.print("Masukan jenis tiket(ANAK/DEWASA) : ");
        jenis_tiket = input.nextLine();
        System.out.print("Masukan harga tiket : ");
        harga = input.nextInt();
        System.out.println("==========================================================");
        String queryInputTiket = "INSERT INTO tiket(nama_tiket,jenis_tiket,harga) VALUES(?,?,?)";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
            PreparedStatement pst = con.prepareStatement(queryInputTiket);
            pst.setString(1, nama_tiket);
            pst.setString(2, jenis_tiket);
            pst.setInt(3, harga);
            pst.executeUpdate();
            System.out.println("Tiket berhasil ditambahkan");
        } catch (SQLException e) {
            System.out.println("Tiket gagal ditambahkan");
        }
    }
}
