/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author User
 */
import model.user;
import repository.usersRepo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SignUp {
    private final usersRepo repo;
    
    public SignUp(){
    this.repo = new usersRepo();
    }
    
    public void show (){
        Scanner sc=new Scanner(System.in);
        
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

        user newUser = new user(
            username,
            email,
            password,
            phoneNumber,
            now,
            createdBy,
            now,
            createdBy
        );
        boolean success = repo.registerUser(newUser);

        if (success) {
            System.out.println("User berhasil didaftarkan.");
        } else {
            System.out.println("Gagal mendaftarkan user.");
        }
    }
}
