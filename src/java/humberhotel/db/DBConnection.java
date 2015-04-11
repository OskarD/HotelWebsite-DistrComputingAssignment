package humberhotel.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oskar
 */
public class DBConnection {
    private static volatile DBConnection instance;
    
    private static Connection connection;
    
    private DBConnection() { 
        try {
            // Load driver
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();

            // Get a Connection to the database
            connection = DriverManager.getConnection("jdbc:oracle:thin:@dilbert.humber.ca:1521:grok", "N00770693", "oracle");
        
            System.out.println("Connection successful!");
        } catch (Exception ex) {
            System.out.println("Could not connect to the database.");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection() {
        if(instance == null) {
            synchronized (DBConnection.class) {
                if(instance == null)
                    instance = new DBConnection();
            }
        }
        
        return connection;
    }
}
