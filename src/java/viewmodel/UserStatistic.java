package viewmodel;

import java.util.UUID;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lenovo
 */
public class UserStatistic {
    private UUID UserID;
    private String status;
    private int total;

    public UserStatistic() {
    }

    public UserStatistic(UUID UserID, String status, int total) {
        this.UserID = UserID;
        this.status = status;
        this.total = total;
    }

    public UUID getUserID() {
        return UserID;
    }

    public void setUserID(UUID UserID) {
        this.UserID = UserID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
