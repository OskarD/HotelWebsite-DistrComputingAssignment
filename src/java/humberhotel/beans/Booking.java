/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.beans;

import java.util.Date;

/**
 *
 * @author Oskar
 */
public class Booking {
    private int
            id;
    
    private Date
            startDate,
            endDate;
    
    private String
            bookedBy;
    
    private int
            roomNumber;
    
    public Booking() {
        
    }

    public int getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    
}
