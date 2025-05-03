import java.sql.Connection;
import config.DatabaseConfig;

import model.user;
import repository.usersRepo;
import ui.SignIn;
import ui.SignUp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Sign Up");
        System.out.println("2. Sign In");
        System.out.print("Pilih opsi: ");
        int pilihan = Integer.parseInt(sc.nextLine());
        
        switch(pilihan){
            case 1:
                SignUp signUp = new SignUp();
                signUp.show();
                break;
            case 2:
                SignIn signIn = new SignIn();
                signIn.show();
                break;
            default:
                System.out.println("Pilihan tidak falid");
        }
    }
}
