import ui.Login;
import repository.supplierRepo;
import model.suppliers;

import javax.swing.JFrame;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() ->{
            //buat jframe
            JFrame frame = new JFrame("INVENTORY APOTEK ROXY");
            
            //JPanel Login ke JFrame
            frame.setContentPane(new Login());
            
            // atur JFrame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    supplierRepo repo = new supplierRepo();
        Scanner scanner = new Scanner(System.in);
        int userId = 1;

        System.out.println("=== MENU ===");
        System.out.println("1. Create Supplier");
        System.out.println("2. Update Supplier");
        System.out.println("3. Delete Supplier");
        System.out.print("Pilih opsi (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // flush newline

        switch (choice) {
            case 1 -> {
                System.out.print("Nama Supplier: ");
                String name = scanner.nextLine();
                System.out.print("Alamat: ");
                String address = scanner.nextLine();
                System.out.print("No. Telepon: ");
                String phone = scanner.nextLine();

                suppliers newSupplier = new suppliers(
                        name, address, phone,
                        LocalDateTime.now(), userId,
                        LocalDateTime.now(), userId
                );
                boolean created = repo.createSupplier(newSupplier);
                System.out.println("CREATE: " + (created ? "Berhasil" : "Gagal"));
            }

            case 2 -> {
                System.out.print("ID Supplier yang akan diubah: ");
                int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("Nama baru: ");
                String name = scanner.nextLine();
                System.out.print("Alamat baru: ");
                String address = scanner.nextLine();
                System.out.print("Telepon baru: ");
                String phone = scanner.nextLine();

                suppliers updateSupplier = new suppliers(
                        name, address, phone,
                        null, 0, // createdAt/by tidak diubah
                        LocalDateTime.now(), userId
                );
                updateSupplier.setId(id);

                boolean updated = repo.updateSupplier(updateSupplier);
                System.out.println("UPDATE: " + (updated ? "Berhasil" : "Gagal"));
            }

            case 3 -> {
                System.out.print("ID Supplier yang akan dihapus: ");
                int id = scanner.nextInt(); scanner.nextLine();
                suppliers delSupplier = new suppliers(null, null, null, null, 0, null, 0);
                delSupplier.setId(id);

                boolean deleted = repo.deleteSuplier(delSupplier);
                System.out.println("DELETE: " + (deleted ? "Berhasil" : "Gagal"));
            }

            default -> System.out.println("Opsi tidak valid.");
        }

        scanner.close();
    }
}
