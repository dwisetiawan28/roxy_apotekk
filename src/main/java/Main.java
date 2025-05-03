import java.sql.Connection;
import config.DatabaseConfig;

import model.user;
import repository.usersRepo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Connection conn = DatabaseConfig.connect();
//        if (conn != null) {
//            System.out.println("Koneksi ke database berhasil!");
//        } else {
//            System.out.println("Gagal terkoneksi ke database.");
//        }
        Scanner sc= new Scanner(System.in);
        
        System.out.println("=== Registrasi User Baru ===");
        System.out.print("Username       : ");
        String username = sc.nextLine();

        System.out.print("Email          : ");
        String email = sc.nextLine();

        System.out.print("Password       : ");
        String password = sc.nextLine();

        System.out.print("Phone Number   : ");
        String phoneNumber = sc.nextLine();
        
        int createdBy = 0;
        
        LocalDateTime now = LocalDateTime.now();
        user user=new user(
            username,
            email,
            password,
            phoneNumber,
            now,
            createdBy,
            now,
            createdBy
        );
        
        usersRepo repo=new usersRepo();
        boolean success = repo.registerUser(user);
        
        if(success){
        System.out.println("User berhasil didaftarkan.");
        }else{
        System.out.println("Gagal mendaftarkan user.");
        }
    }
}
