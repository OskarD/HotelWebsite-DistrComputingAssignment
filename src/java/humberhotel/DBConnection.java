/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberhotel;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Serio
 */
public class DBConnection {
    private static volatile Connection dbc;
    
    public static Connection connectDB() {
        if (dbc == null) {
            synchronized (DBConnection.class) {
                if (dbc == null) {
                    try {
                        Class.forName("oracle.jdbc.OracleDriver");
                        dbc = DriverManager.getConnection("jdbc:oracle:thin:@dilbert.humber.ca:1521:grok", "N00770693", "oracle");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dbc;
    }
    
    
}
