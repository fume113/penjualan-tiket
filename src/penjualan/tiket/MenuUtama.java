package penjualan.tiket;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuUtama {
    Storage storage = Storage.getInstance();
    TampilTiket tampilTiket = new TampilTiket();
    Belitiket belitiket = new Belitiket();
    ReedemVoucher reedemVoucher = new ReedemVoucher();
    ViewProfile viewProfile = new ViewProfile();
    Buattiketadmin buattiketadmin = new Buattiketadmin();
    BuatVoucher buatVoucher = new BuatVoucher();
    VerifikasiTiket verifikasiTiket = new VerifikasiTiket();
    LihatVoucher lihatVoucher = new LihatVoucher();
    LihatSaldo lihatSaldo = new LihatSaldo();
    public void mainmenu() {
        Scanner input = new Scanner(System.in);

        int menuChoice;
        if (storage.getRole().equals("admin")) {
            do {
                System.out.flush();
                System.out.println("");
                System.out.println("Selamat datang admin Wonderland");

                System.out.println("1: Buat Tiket");
                System.out.println("2: Buat Voucher");
                System.out.println("3: Verifikasi Tiket");
                System.out.println("4: Lihat Voucher");
                System.out.println("5: Logout");
                System.out.print("Pilih : ");
                menuChoice = input.nextInt();

                switch (menuChoice) {

                    case 1:
                        buattiketadmin.viewbuattiket();
                        break;

                    case 2:
                        buatVoucher.viewBuatVoucher();
                        break;

                    case 3:
                        verifikasiTiket.viewVerifikasi();
                        break;
                    case 4:
                        lihatVoucher.viewLihatVoucher();
                        break;
                    case 5:
                        System.out.println("================================");
                        System.out.println("= Terimakasih Telah Berkunjung =");
                        System.out.println("================================");
                        break;

                    default:
                        System.out.println("ENTER A VALID INPUT");
                }

            } while (menuChoice != 5);
            // input.close();

        } else {
            do {
                System.out.flush();
                System.out.println("");
                System.out.println("Selamat Datang di Wonderland");
                System.out.println("1: Lihat Tiket");
                System.out.println("2: Beli Tiket");
                System.out.println("3: Tukar Voucher");
                System.out.println("4: Lihat Profil");
                System.out.println("5: Lihat Saldo");
                System.out.println("6: Logout");

                System.out.print("Pilih : ");
                menuChoice = input.nextInt();
                switch (menuChoice) {

                    case 1:
                        tampilTiket.tampilTiket();
                        break;

                    case 2:
                        try {
                            belitiket.viewbelitiket();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;

                    case 3:
                        reedemVoucher.viewReedem();
                        break;

                    case 4:
                        viewProfile.viewProfile();
                        break;
                    case 5:
                        lihatSaldo.viewSaldo();
                        break;
                    case 6:
                        System.out.println("================================");
                        System.out.println("= Terimakasih Telah Berkunjung =");
                        System.out.println("================================");
                        System.exit(0);

                    default:
                        System.out.println("ENTER A VALID INPUT");
                }

            } while (menuChoice != 6);
            // input.close();

        }

    }
}
