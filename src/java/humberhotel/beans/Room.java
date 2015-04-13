/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.beans;

import humberhotel.db.DBConnection;
import humberhotel.exception.HotelException;
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
public class Room {
    private static final String 
        QUERY_CREATE = 
            "INSERT INTO hotelrooms (roomnumber, type) VALUES (?, ?)",
        QUERY_DELETE =
            "DELETE FROM hotelrooms WHERE roomnumber = ?",
        QUERY_GET =
            "SELECT * FROM hotelrooms WHERE roomnumber = ?",
        QUERY_GET_ALL =
            "SELECT * FROM hotelrooms";
    
    private int
            roomNumber;
    
    private String
            type;
    
    public Room() {
    
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public static final void create(int roomNumber, String type) throws SQLException {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_CREATE)) {
            stmt.setInt(1, roomNumber);
            stmt.setString(2, type);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public static final void delete(int roomNumber) throws SQLException {
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_DELETE)) {
            stmt.setInt(1, roomNumber);
            stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public static final Room get(int roomNumber) throws SQLException, HotelException {
        Room room = new Room();
        
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_GET)) {
            stmt.setInt(1, roomNumber);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next() == false)
                throw new HotelException("There is no room with this number");
            
            room.setRoomNumber(rs.getInt(1));
            room.setType(rs.getString(2));

        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        return room;
    }
    
    public static final ArrayList<Room> getAll() throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(QUERY_GET_ALL)) {
            ResultSet rs = stmt.executeQuery();
            
            Room room;
            
            while(rs.next()) {
                room = new Room();
                room.setRoomNumber(rs.getInt(1));
                room.setType(rs.getString(2));
                rooms.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        
        return rooms;
    }
}
