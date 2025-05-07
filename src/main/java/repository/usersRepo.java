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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;

public class usersRepo {
    public boolean registerUser(user users){
        String sql ="INSERT INTO users(username, email, password, phone_number, created_at, created_by, updated_at, updated_by)"+"VALUES(?,?,?,?,?,?,?,?)";
            try(Connection conn = DatabaseConfig.connect();
                    PreparedStatement stmt=conn.prepareStatement(sql)){
            
                if(conn == null){
                    System.err.println("Koneksi null. Gagal menyimpan user.");
                    return false;
                }
                
                stmt.setString(1, users.getUserName());
                stmt.setString(2, users.getEmail());
                stmt.setString(3, users.getPassword());
                stmt.setString(4, users.getPhoneNumber());
                stmt.setTimestamp(5, Timestamp.valueOf(users.getCreatedAt()));
                stmt.setInt(6, users.getCreatedBy());
                stmt.setTimestamp(7, Timestamp.valueOf(users.getUpdatedAt()));
                stmt.setInt(8, users.getUpdatedBy());
                
                int rows = stmt.executeUpdate();
                return rows>0;
            }catch (SQLException e) {
                System.err.println("Error SQL: " + e.getMessage());
                return false;
            }
    }
    
    // repository/usersRepo.java
    public user signIn(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
    
        try (Connection conn = DatabaseConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            stmt.setString(1, email);
            stmt.setString(2, password);
        
            ResultSet rs = stmt.executeQuery();
        
            if (rs.next()) {
                return new user(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone_number"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getInt("created_by"),
                    rs.getTimestamp("updated_at").toLocalDateTime(),
                    rs.getInt("updated_by")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error SQL SignIn: " + e.getMessage());
        }
        return null;
    }
}
