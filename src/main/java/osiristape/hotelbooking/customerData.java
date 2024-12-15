/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osiristape.hotelbooking;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author shift
 */
public class customerData {

    private int id;
    private String roomType;
    private String roomNumber;
    private int quantity;
    private double total;
    private Date date;
    private Time time;

    // Constructor
    public customerData(int id, String roomType, String roomNumber, int quantity, double total, Date date, Time time) {
        this.id = id;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.time = time;
    }

    // Getter and Setter methods for all the fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
