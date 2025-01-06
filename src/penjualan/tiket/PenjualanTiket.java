package penjualan.tiket;

import java.util.Scanner;

public class PenjualanTiket {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (
                Scanner input = new Scanner(System.in).useDelimiter("\n")) {
            RegistrasiUser user = new RegistrasiUser();
            Login login = new Login();
            Storage storage = Storage.getInstance();
            MenuUtama menuUtama = new MenuUtama();
            System.out.println("================================");
            System.out.println("= Selamat Datang di Wonderland =");
            System.out.println("================================");
            System.out.println("");
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("");
            System.out.print("Pilih : ");
            int pilih = input.nextInt();
            if (pilih == 1) {
                System.out.println("");
                System.out.println("=========================");
                System.out.println("=      LOGIN USER       =");
                System.out.println("=========================");
                System.out.println("");
                login.viewLogin();
            }
            if (pilih == 2) {
                System.out.println("");
                System.out.println("=========================");
                System.out.println("=    Registrasi User    =");
                System.out.println("=========================");
                System.out.println("");
                user.viewRegister();
            }
            if (storage.getRole().equals("admin")) {
                System.out.println("");
                System.out.println("================================");
            }
            System.out.println("");
            menuUtama.mainmenu();
            
        }
    }
}
