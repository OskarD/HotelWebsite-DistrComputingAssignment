/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.beans;

import humberhotel.db.DBConnection;
import humberhotel.exception.HotelException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oskar
 */
public class Booking {
    private static final String 
        QUERY_AVAILABILITY =
            "SELECT DISTINCT " +
            "  days.bdate" +
            "FROM " +
            "  HOTELBOOKEDDAYS days" +
            "JOIN" +
            "  HOTELBOOKINGS bookings" +
            "ON" +
            "  days.bookingid = bookings.id" +
            "WHERE" +
            "  bookings.roomnumber = ?" +
            "AND" +
            "  days.bdate >= ?" +
            "AND" +
            "  days.bdate <= ?",
        QUERY_GETBOOKING =
            "SELECT * FROM hotelbookings WHERE id = ?";
    
    
    
    private int
            id;
    
    private String
            bookedBy;
    
    private int
            roomNumber;
    
    private ArrayList<BookedDay> 
            days = new ArrayList<BookedDay>();
    
    public Booking() {
        
    }

    public int getId() {
        return id;
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

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ArrayList<BookedDay> getDays() {
        return days;
    }

    public void setDays(ArrayList<BookedDay> days) {
        this.days = days;
    }
    
    public void addDay(BookedDay day) {
        this.days.add(day);
    }
    
    public void create() throws HotelException {
        if(!Booking.checkAvailability(this.roomNumber, null, null))
            throw new HotelException("The room is unavailable for this period", HotelException.BOOKING_ROOM_UNAVAILABLE);
        
        
    }
    
    public static boolean checkAvailability(int roomNumber, Date startDate, Date endDate) throws SQLException {
        ResultSet rs;

        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_AVAILABILITY)) {
            stmt.setInt(1, roomNumber);
            stmt.setDate(2, startDate);
            stmt.setDate(3, endDate);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        return rs.next() != false; // Returns false if there are any bookings between the dates
    }
    
    public static Booking getBooking(int id) throws HotelException, SQLException {
        ResultSet rs;

        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_GETBOOKING)) {
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next() == false)
                throw new HotelException("This room does not exist", HotelException.BOOKING_ROOM_DOES_NOT_EXIST);
        
            return rs.getObject("Booking", Booking.class);
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
