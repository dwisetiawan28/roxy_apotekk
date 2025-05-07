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
import model.suppliers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.ResultSet;

public class supplierRepo {
    public boolean createSupplier(suppliers spl){
        String query = "INSERT INTO suppliers(supplier_name, address, phone, created_at, created_by, updated_at, updated_by)"+"VALUES(?,?,?,?,?,?,?)";
        if (query == null){
            System.out.println("Gagal menyimpan Suplier");
        }
        try(Connection conn = DatabaseConfig.connect(); 
                PreparedStatement stmt=conn.prepareStatement(query)){
            if(conn == null){
                System.out.println("Koneksi null. Gagal menyimpan Supplier.");
            }
            
            stmt.setString(1, spl.getSupplierName());
            stmt.setString(2, spl.getAddress());
            stmt.setString(3, spl.getPhone());
            stmt.setTimestamp(4, Timestamp.valueOf(spl.getCreatedAt()));
            stmt.setInt(5, spl.getCreatedBy());
            stmt.setTimestamp(6, Timestamp.valueOf(spl.getUpdatedAt()));
            stmt.setInt(7, spl.getUpdatedBy());
            
            int rows = stmt.executeUpdate();
            return rows>0;
        }catch(SQLException e){
            System.out.println("Error query: "+ e.getMessage());
            return false;
        }
    }
    
    public boolean updateSupplier(suppliers spl){
        String query = "UPDATE suppliers SET supplier_Name=?, address=?, phone=?, updated_at=?, updated_by=? WHERE id=?";
        if(query==null){
            System.out.println("Gagal update Suplier");
        }
        try(Connection conn = DatabaseConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, spl.getSupplierName());
            stmt.setString(2, spl.getAddress());
            stmt.setString(3, spl.getPhone());
            stmt.setTimestamp(4, Timestamp.valueOf(spl.getUpdatedAt()));
            stmt.setInt(5, spl.getUpdatedBy());
            stmt.setInt(6,spl.getId());
            
            int rows = stmt.executeUpdate();
            return rows>0;
        }catch(SQLException e){
            System.out.println("Error query: "+ e.getMessage());
            return false;
        }
    }
    
    public boolean deleteSuplier(suppliers spl){
        String query ="DELETE FROM suppliers WHERE id=?";
        if(query==null){
            System.out.println("Gagal update Suplier");
        }
        try(Connection conn = DatabaseConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, spl.getId());
            int rows = stmt.executeUpdate();
            return rows>0;
        } catch(SQLException e){
           System.out.println("Error query: "+ e.getMessage());
            return false; 
        }
    }
}
