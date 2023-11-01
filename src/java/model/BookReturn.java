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
public class BookReturn {

    private UUID bookReturnID;
    private UUID userID;
    private UUID bookID;
    private UUID fineID;
    private String status;
    private String ISBN;
    private LocalDate beginDate;
    private LocalDate endDate;

    public BookReturn() {
        status = "OK";
    }

    
    public BookReturn(UUID bookReturnID, UUID userID, UUID bookID, UUID fineID, String status, String ISBN, LocalDate beginDate, LocalDate endDate) {
        this.bookReturnID = bookReturnID;
        this.userID = userID;
        this.bookID = bookID;
        this.fineID = fineID;
        this.status = status;
        this.ISBN = ISBN;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public UUID getBookReturnID() {
        return bookReturnID;
    }

    public void setBookReturnID(UUID bookReturnID) {
        this.bookReturnID = bookReturnID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getBookID() {
        return bookID;
    }

    public void setBookID(UUID bookID) {
        this.bookID = bookID;
    }

    public UUID getFineID() {
        return fineID;
    }

    public void setFineID(UUID fineID) {
        this.fineID = fineID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    

}
