/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel.exception;

/**
 *
 * @author Oskar
 */
public class HotelException extends Exception {
    
    public static final int
            BOOKING_ROOM_UNAVAILABLE    = 1;
    
    private int
            code;
    
    public HotelException(String message) {
        super(message);
    }
    
    public HotelException(String message, int code) {
        super(message);
        this.code = code;
    }
    
}
