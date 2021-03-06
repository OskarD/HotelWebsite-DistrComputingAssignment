/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.beans;

import humberhotel.db.DBConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oskar
 */
public class BookedDay {
    private static final String 
        QUERY_CREATE = 
            "INSERT INTO HOTELBOOKEDDAYS (bookingid, bdate) VALUES (?, ?)",
        QUERY_GET =
            "SELECT * FROM hotelbookeddays WHERE bookingid = ?";
    
    private int
            id,
            bookingId;
    
    private Date
            bDate;
    
    public BookedDay() {
        
    }

    public int getId() {
        return id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Date getBDate() {
        return bDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setBDate(Date bDate) {
        this.bDate = bDate;
    }
    
    public void create() throws SQLException {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_CREATE)) {
            stmt.setInt(1, getBookingId());
            stmt.setDate(2, getBDate());
            
            stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BookedDay.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public static ArrayList<BookedDay> get(int bookingId) throws SQLException {
        ArrayList<BookedDay> days = new ArrayList<>();
        
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_GET)) {
            ResultSet rs = stmt.executeQuery();
            
            BookedDay day;
            
            while(rs.next()) {
                day = new BookedDay();
                day.setId(rs.getInt(1));
                day.setBookingId(rs.getInt(2));
                day.setBDate(rs.getDate(3));
                days.add(day);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        return days;
    }
}
