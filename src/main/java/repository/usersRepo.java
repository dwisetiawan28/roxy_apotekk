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
                System.err.println("❌ Error SQL: " + e.getMessage());
                return false;
            }
    }
}
