/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.tiket;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author SHANIA
 */
public class Login {
    private String username, password, role, nama, alamat, no_telp;
    private int id_user;
    Storage storage = Storage.getInstance();

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNama() {
        return nama;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public int getId_user() {
        return id_user;
    }
    public void login() throws SQLException {
        String query = "SELECT * FROM user WHERE username=? AND password=?";
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pst = con.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);

        ResultSet res = pst.executeQuery();
        if (res.next()) {
            this.setRole(res.getString("role"));
            this.setNama(res.getString("nama"));
            this.setAlamat(res.getString("alamat"));
            this.setNo_telp(res.getString("no_hp"));
            this.setId_user(res.getInt("id_user"));
            storage.setNama(this.getNama());
            storage.setRole(this.getRole());
            storage.setUser_id(this.getId_user());
            storage.setUsername(this.getUsername());
            con.close();
        } else {
            con.close();
            System.out.println("Username atau password salah");
            System.exit(0);
        }
    }

    public void viewLogin() {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Username : ");
        this.setUsername(input.next());
        System.out.print("Password : ");
        this.setPassword(input.next());
        try {
            this.login();
        } catch (SQLException e) {
            System.out.println("");
            System.out.println("Username atau Password tidak sesuai");
            e.printStackTrace();
        }
    }
}