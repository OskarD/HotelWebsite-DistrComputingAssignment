/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.beans;

/**
 *
 * @author Oskar
 */
public class BookedDay {
    private int
            id,
            bookedNumber;
    
    private String
            bookedBy;
    
    public BookedDay() {
        
    }

    public int getId() {
        return id;
    }

    public int getBookedNumber() {
        return bookedNumber;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookedNumber(int bookedNumber) {
        this.bookedNumber = bookedNumber;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }
    
    
}
