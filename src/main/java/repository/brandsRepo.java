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
import model.brands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class brandsRepo {
    Connection conn = DatabaseConfig.connect();
    
    public boolean createBrands(brands brd){
        String query ="INSERT INTO brands(brand_name, supplier_id, created_at, created_by, updated_at, updated_by)"+"VALUES(?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt=conn.prepareStatement(query)){
            if(conn == null){
                System.out.println("Koneksi null. Gagal menyimpan brand.");
            }
            
            stmt.setString(1, brd.getBrandName());
            stmt.setInt(2, brd.getSupplierId());
            stmt.setTimestamp(3, Timestamp.valueOf(brd.getCreatedAt()));
            stmt.setInt(4, brd.getCreatedBy());
            stmt.setTimestamp(5, Timestamp.valueOf(brd.getUpdatedAt()));
            stmt.setInt(6, brd.getUpdatedBy());
            
            int rows = stmt.executeUpdate();
            return rows>0;
        }catch (SQLException e){
            System.out.println("Error query: "+ e.getMessage());
            return false;
        }
    }
    
    public boolean updateBrand(brands brd){
        String query = "UPDATE brands SET brand_name=?, supplier_id=?, updated_at=?, updated_by=? WHERE id=?";
        try(PreparedStatement stmt=conn.prepareStatement(query)){
            if(conn == null){
                System.out.println("Koneksi null. Gagal menyimpan brand.");
            }
            stmt.setString(1, brd.getBrandName());
            stmt.setInt(2, brd.getSupplierId());
            stmt.setTimestamp(3, Timestamp.valueOf(brd.getUpdatedAt()));
            stmt.setInt(4, brd.getUpdatedBy());
            stmt.setInt(5,brd.getId());
            int rows=stmt.executeUpdate();
            return rows>0;
        }catch (SQLException e){
            System.out.println("Error query: "+ e.getMessage());
            return false;
        }    
    }
    
    public boolean deleteBrand(brands brd){
        String query ="DELETE FROM brands WHERE id=?";
        try(PreparedStatement stmt=conn.prepareStatement(query)){
            if(conn == null){
                System.out.println("Koneksi null. Gagal menyimpan brand.");
            }
            stmt.setInt(1, brd.getId());
            int rows = stmt.executeUpdate();
            return rows>0;
        }catch (SQLException e){
            System.out.println("Error query: "+ e.getMessage());
            return false;
        }
    }
}
