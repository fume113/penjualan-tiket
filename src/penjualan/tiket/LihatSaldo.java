package penjualan.tiket;

import java.sql.*;

public class LihatSaldo {
    Storage storage = Storage.getInstance();

    public void viewSaldo() {
        String queryViewSaldo = "SELECT * FROM saldo where id_user = '" + storage.getUser_id() + "'";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/penjualan_tiket", "root",
                    "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryViewSaldo);
            System.out.println("===========================================================");
            while (resultSet.next()) {
                System.out.println("Saldo Anda : " + resultSet.getInt("saldo"));
            }
            System.out.println("===========================================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
