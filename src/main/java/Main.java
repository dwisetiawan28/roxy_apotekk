import ui.Login;
import repository.itemsRepo;
import model.items;

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
        itemsRepo repo = new itemsRepo();
        Scanner sc = new Scanner(System.in);
        int userId = 1;

        System.out.println("=== MENU ===");
        System.out.println("1. Create item");
        System.out.println("2. Update item");
        System.out.println("3. Delete item");
        System.out.print("Pilih opsi (1/2/3): ");
        int choice = sc.nextInt();
        sc.nextLine(); // flush newline

        switch (choice) {
            case 1 -> {
                System.out.print("Item Name: ");
                String itemName = sc.nextLine();
                System.out.print("Brand Id: ");
                int brandID = sc.nextInt();
                System.out.print("Price: ");
                int price = sc.nextInt();

                items newItems = new items(
                        itemName,brandID,price,
                        LocalDateTime.now(), userId,
                        LocalDateTime.now(), userId
                );
                boolean created = repo.createItem(newItems);
                System.out.println("CREATE: " + (created ? "Berhasil" : "Gagal"));
            }

            case 2 -> {
                System.out.print("ID Item Name to be changed: ");
                int id = sc.nextInt(); sc.nextLine();
                System.out.print("New Item Name: ");
                String itemName = sc.nextLine();
                System.out.print("New Brand ID: ");
                int brandId = sc.nextInt();
                System.out.print("Price: ");
                int price = sc.nextInt();

                items updateItems = new items(
                        itemName,brandId,price,
                        null, 0, // createdAt/by tidak diubah
                        LocalDateTime.now(), userId
                );
                updateItems.setId(id);

                boolean updated = repo.updateItem(updateItems);
                System.out.println("UPDATE: " + (updated ? "Berhasil" : "Gagal"));
            }

            case 3 -> {
                System.out.print("ID Brand Name to be deleted: ");
                int id = sc.nextInt(); sc.nextLine();
                items delItem = new items(null, 0, 0, null, 0, null, 0);
                delItem.setId(id);

                boolean deleted = repo.deleteItem(delItem);
                System.out.println("DELETE: " + (deleted ? "Berhasil" : "Gagal"));
            }

            default -> System.out.println("Opsi tidak valid.");
        }

        sc.close();
    }
}
