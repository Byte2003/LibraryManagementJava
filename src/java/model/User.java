/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author lenovo
 */
public class User {
    private UUID UserID;
    private String name;
    private String email;
    private String Password;
    private String PhoneNumber;
    private String Image;
    private String Address;
    private LocalDate RegisterDate;
    private String role;

    public User() {
    }

    
    public User(UUID UserID, String name, String email, String Password, String PhoneNumber, String Image, String Address, LocalDate RegisterDate, String role) {
        this.UserID = UserID;
        this.name = name;
        this.email = email;
        this.Password = Password;
        this.PhoneNumber = PhoneNumber;
        this.Image = Image;
        this.Address = Address;
        this.RegisterDate = RegisterDate;
        this.role = role;
    }

    public UUID getUserID() {
        return UserID;
    }

    public void setUserID(UUID UserID) {
        this.UserID = UserID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public LocalDate getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(LocalDate RegisterDate) {
        this.RegisterDate = RegisterDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
