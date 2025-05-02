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

public class user {
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    
    public user(String email, String password, String username, String phoneNumber, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy){
        this.email=email;
        this.password=password;
        this.username=username;
        this.phoneNumber=phoneNumber;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.createdBy=createdBy;
        this.updatedBy=updatedBy;
    }
    
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getUserName(){
        return username;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
    public String getCreatedBy(){
        return createdBy;
    }
    public String getUpdatedBy(){
        return updatedBy;
    }
}