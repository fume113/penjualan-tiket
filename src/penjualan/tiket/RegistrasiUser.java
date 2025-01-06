package penjualan.tiket;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrasiUser {
    private String nama, alamat, noTelp, email, username, password;
    Storage storage = Storage.getInstance();
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(email);
        if (match.matches()) {
            this.email = email;
        } else {
            System.out.println("Email tidak valid");
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String PATTERN = "^[a-zA-Z\\s]{0,50}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(nama);
        if (match.matches()) {
            this.nama = nama;
        } else {
            System.out.println("Nama tidak valid");
        }
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        String PATTERN = "^[0-9]{0,13}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(noTelp);
        if (match.matches()) {
            this.noTelp = noTelp;
        } else {
            System.out.println("No. Telp tidak valid");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void register() throws SQLException {
        String query = "INSERT INTO `user`(`nama`, `username`, `password`, `no_hp`, `alamat`, `email`, `role`) VALUES (?, ? , ? , ?, ?, ?, ?)";
        String querySaldo = "INSERT INTO `saldo` (`id_user`, `saldo`) VALUES (? ,?)";
        Connection con = null;
        PreparedStatement pst = null, saldoQuery = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root", "");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, nama);
        pst.setString(2, username);
        pst.setString(3, password);
        pst.setString(4, noTelp);
        pst.setString(5, alamat);
        pst.setString(6, email);
        pst.setString(7, "user");

        if (nama.equals("") || username.equals("") || password.equals("") || noTelp.equals("") || alamat.equals("")
                || email.equals("")) {
            System.out.println("Data tidak boleh kosong");
        } else {
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    
                    saldoQuery = con.prepareStatement(querySaldo);
                    saldoQuery.setInt(1, generatedKeys.getInt(1));
                    saldoQuery.setInt(2, 0);
                    saldoQuery.executeUpdate();
                    storage.setNama(nama);
                    storage.setRole("user");
                    storage.setUser_id(generatedKeys.getInt(1));
                    storage.setUsername(username);
                    con.close();
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            System.out.println("Data berhasil ditambahkan");
        }
    }

    public void viewRegister() {
        try {
            Scanner sc = new Scanner(System.in).useDelimiter("\n");
            System.out.print("Nama : ");
            setNama(nama = sc.nextLine());
            System.out.print("Username : ");
            setUsername(username = sc.nextLine());
            System.out.print("Password : ");
            setPassword(password = sc.nextLine());
            System.out.print("No. Telp : ");
            setNoTelp(noTelp = sc.nextLine());
            System.out.print("Alamat : ");
            setAlamat(alamat = sc.nextLine());
            System.out.print("Email : ");
            setEmail(email = sc.nextLine());
            this.register();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
