package humberhotel.beans;

import humberhotel.db.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Serio
 */
public class User {
    private String email;
    private String name;
    private int authority;
    
    public User() {
        
    }
    
    public User(String email, String name, int authority) {
        this.email = email;
        this.name = name;
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
    
    public static User login(String email, String password) {
        String passwordDB = "";
        Connection dbc = DBConnection.getConnection();
        ResultSet rs = null;
        User user = null;
        try {
            Statement stmt = dbc.createStatement();
            rs = stmt.executeQuery("SELECT * FROM N00770693.HOTELUSERS WHERE email = '" + email + "'");
            while (rs.next()) {
                passwordDB = rs.getString("password");
                if (password.equalsIgnoreCase(passwordDB)) {
                    String name = rs.getString("name");
                    int authority = rs.getInt("admin");
                    user = new User(email, name, authority);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }        
}
