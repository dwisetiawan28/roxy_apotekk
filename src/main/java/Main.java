import ui.Login;
import repository.brandsRepo;
import model.brands;

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
        brandsRepo repo = new brandsRepo();
        Scanner scanner = new Scanner(System.in);
        int userId = 1;

        System.out.println("=== MENU ===");
        System.out.println("1. Create Brand");
        System.out.println("2. Update Brand");
        System.out.println("3. Delete Brand");
        System.out.print("Pilih opsi (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // flush newline

        switch (choice) {
            case 1 -> {
                System.out.print("Brand Name: ");
                String brandName = scanner.nextLine();
                System.out.print("Supplier Id: ");
                int supplierId = scanner.nextInt();

                brands newBrands = new brands(
                        brandName,supplierId,
                        LocalDateTime.now(), userId,
                        LocalDateTime.now(), userId
                );
                boolean created = repo.createBrands(newBrands);
                System.out.println("CREATE: " + (created ? "Berhasil" : "Gagal"));
            }

            case 2 -> {
                System.out.print("ID Brand Name to be changed: ");
                int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("New Brand Name: ");
                String brandName = scanner.nextLine();
                System.out.print("New Supplier ID: ");
                int supplierId = scanner.nextInt();

                brands updateBrand = new brands(
                        brandName,supplierId,
                        null, 0, // createdAt/by tidak diubah
                        LocalDateTime.now(), userId
                );
                updateBrand.setId(id);

                boolean updated = repo.updateBrand(updateBrand);
                System.out.println("UPDATE: " + (updated ? "Berhasil" : "Gagal"));
            }

            case 3 -> {
                System.out.print("ID Brand Name to be deleted: ");
                int id = scanner.nextInt(); scanner.nextLine();
                brands delBrand = new brands(null, 0, null, 0, null, 0);
                delBrand.setId(id);

                boolean deleted = repo.deleteBrand(delBrand);
                System.out.println("DELETE: " + (deleted ? "Berhasil" : "Gagal"));
            }

            default -> System.out.println("Opsi tidak valid.");
        }

        scanner.close();
    }
}
