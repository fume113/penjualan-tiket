package penjualan.tiket;

import java.sql.*;

public class LihatVoucher {

    public void viewLihatVoucher() {
        String queryViewVoucher = "SELECT * FROM voucher";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root",
                    "");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(queryViewVoucher);
            String leftAlignFormat = "| %-4d | %-19s | %-11d | %-11s |%n";
            System.out.format("+------+---------------------+-------------+-------------+%n");
            System.out.format("| ID   | Kode Voucher        | Nominal     | Status      |%n");
            System.out.format("+------+---------------------+-------------+-------------+%n");
            while (resultSet.next()) {
                int id_voucher = resultSet.getInt("id_voucher");
                int nominal = resultSet.getInt("nominal");
                String kode_voucher = resultSet.getString("kode_voucher");
                String status = resultSet.getString("status");
                System.out.format(leftAlignFormat, id_voucher, kode_voucher, nominal, status);
            }
            System.out.format("+------+---------------------+-------------+-------------+%n");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
