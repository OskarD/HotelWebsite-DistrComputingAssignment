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
            "SELECT DISTINCT" +
            "  days.bdate " +
            "FROM" +
            "  HOTELBOOKEDDAYS days " +
            "JOIN" +
            "  HOTELBOOKINGS bookings " +
            "ON" +
            "  days.bookingid = bookings.id " +
            "WHERE" +
            "  bookings.roomnumber = ? " +
            "AND" +
            "  days.bdate >= ? " +
            "AND" +
            "  days.bdate <= ? ",
        QUERY_GET =
            "SELECT * FROM hotelbookings WHERE id = ?",
        QUERY_GET_ALL =
            "SELECT * FROM hotelbookings",
        QUERY_CREATE =
            "INSERT INTO HOTELBOOKINGS (bookedby, roomnumber) VALUES (?, ?)",
        QUERY_DELETE =
            "DELETE FROM hotelbookings WHERE id = ?";
    
    
    
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
    
    public static boolean checkAvailability(int roomNumber, Date startDate, Date endDate) throws SQLException {

        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_AVAILABILITY)) {
            stmt.setInt(1, roomNumber);
            stmt.setDate(2, startDate);
            stmt.setDate(3, endDate);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next() != false) // If there are any results, the room is taken
                return false;
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public void create() throws HotelException, SQLException {
        if(!Booking.checkAvailability(this.roomNumber, days.get(0).getBDate(), days.get(days.size() - 1).getBDate()))
            throw new HotelException("The room is unavailable for this period", HotelException.BOOKING_ROOM_UNAVAILABLE);
        
        if(getBookedBy() == null)
            throw new HotelException("Booking does not have a user connected to it");
        
        if(getRoomNumber() == 0)
            throw new HotelException("Booking does not have a room number connected to it");
        
        if(days.isEmpty())
            throw new HotelException("There are no days in this booking");
        
        String generatedColumns[] = { "ID" };
        
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_CREATE, generatedColumns)) {
            stmt.setString(1, getBookedBy());
            stmt.setInt(2, getRoomNumber());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next() == false)
                throw new HotelException("Could not get ID of inserted Booking");
            
            for(BookedDay day : getDays()) {
                day.setBookingId(rs.getInt(1));
                day.create();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public static Booking get(int id) throws HotelException, SQLException {

        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_GET)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next() == false)
                throw new HotelException("This room does not exist", HotelException.BOOKING_ROOM_DOES_NOT_EXIST);
        
            return rs.getObject("Booking", Booking.class);
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public static final ArrayList<Booking> getAll() throws SQLException {
        ArrayList<Booking> bookings = new ArrayList<>();
        
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_GET_ALL)) {
            ResultSet rs = stmt.executeQuery();
            
            Booking booking;
            
            while(rs.next()) {
                booking = new Booking();
                booking.setId(rs.getInt(1));
                booking.setBookedBy(rs.getString(2));
                booking.setRoomNumber(rs.getInt(3));
                bookings.add(booking);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        return bookings;
    }
    
    public static void delete(int id) throws HotelException, SQLException {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
