/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author User
 */

import config.DatabaseConfig;
import model.user;
import helper.currentUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

public class usersRepo {
    //cek format email
    private boolean isValidGmail(String email) {
        return email != null && email.endsWith("@gmail.com");
    }

    //cek format phone number
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{11,}");
    }
    
    // Cek email uniq
    private boolean isEmailExist(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    // Cek nomor hp uniq
    private boolean isPhoneNumberExist(String phoneNumber) throws SQLException {
        String sql = "SELECT * FROM users WHERE phone_number = ?";
        try (Connection conn = DatabaseConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, phoneNumber);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    //register user
    public boolean registerUser(user users){
        String sql ="INSERT INTO users(username, email, password, phone_number, created_at, created_by, updated_at, updated_by)"+"VALUES(?,?,?,?,?,?,?,?)";
            try (Connection conn = DatabaseConfig.connect()) {
            if (conn == null) {
                System.err.println("Null connection. registration failed!");
                return false;
            }
            
            if (!isValidGmail(users.getEmail())) {
                System.out.println("Email must use the @gmail.com domain!");
                return false;
            }

            if (!isValidPhoneNumber(users.getPhoneNumber())) {
                System.out.println("Mobile phone number must consist of at least 11 digit!");
                return false;
            }

            if (isEmailExist(users.getEmail())) {
                System.out.println("Email has been registered!");
                return false;
            }

            if (isPhoneNumberExist(users.getPhoneNumber())) {
                System.out.println("Phone number has been registered!");
                return false;
            }

            // Hash password
            String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, users.getUserName());
                stmt.setString(2, users.getEmail());
                stmt.setString(3, hashedPassword);
                stmt.setString(4, users.getPhoneNumber());
                stmt.setTimestamp(5, Timestamp.valueOf(users.getCreatedAt()));
                stmt.setInt(6, users.getCreatedBy());
                stmt.setTimestamp(7, Timestamp.valueOf(users.getUpdatedAt()));
                stmt.setInt(8, users.getUpdatedBy());

                int rows = stmt.executeUpdate();
                return rows > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

    
    // login user
    public user loginUser (String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";
    
        try (Connection conn = DatabaseConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            stmt.setString(1, email);
//            stmt.setString(2, password);
        
            ResultSet rs = stmt.executeQuery();
        
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)){
                user loggedInUser = new user(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    hashedPassword,
                    rs.getString("phone_number"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getInt("created_by"),
                    rs.getTimestamp("updated_at").toLocalDateTime(),
                    rs.getInt("updated_by")
                );
                // Atur current user hanya jika login berhasil
                currentUser.set(loggedInUser.getId(), loggedInUser.getUserName());
                return loggedInUser;
                }else{
                    System.out.println("email or password does'nt matched!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL SignIn: " + e.getMessage());
        }
        return null;
    }
}
