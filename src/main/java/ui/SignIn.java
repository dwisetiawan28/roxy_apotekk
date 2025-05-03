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

import java.util.Scanner;

public class SignIn {
    private final usersRepo repo;

    public SignIn() {
        repo = new usersRepo();
    }

    public void show() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== LOGIN ===");
        System.out.print("Email     : ");
        String email = sc.nextLine();
        System.out.print("Password  : ");
        String password = sc.nextLine();
        
        user result = repo.signIn(email, password);
        
        if (result != null) {
            System.out.println("Login berhasil. Selamat datang, " + result.getUserName() + "!");
        } else {
            System.out.println("Email atau password salah!");
        }
    }
}
