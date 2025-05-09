/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
import java.time.LocalDateTime;

public class items {
    private int id;
    private String itemName;
    private int brandId;
    private int price;
    private LocalDateTime createdAt;
    private int createdBy;
    private LocalDateTime updatedAt;
    private int updatedBy;
    
    public items(String itemName, int branID, int price, LocalDateTime createdAt, int createdBy,  LocalDateTime updatedAt, int updatedBy){
        this.itemName=itemName;
        this.brandId=brandId;
        this.price=price;
        this.createdAt=createdAt;
        this.createdBy=createdBy;
        this.updatedAt=updatedAt;
        this.updatedBy=updatedBy;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getItemName(){
        return itemName;
    }
    public void setItemName(String itemName){
        this.itemName=itemName;
    }
    public int getBrandId(){
        return brandId;
    }
    public void setBrandId(int brandId){
        this.brandId=brandId;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price=price;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public int getCreatedBy(){
        return createdBy;
    }
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public int getUpdatedBy(){
        return updatedBy;
    }
    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }
}
