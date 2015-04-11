package humberhotel;

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
    private boolean authority;
    
    public User() {
        
    }
    
    public User(String email, String name, boolean authority) {
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

    public boolean getAuthority() {
        return authority;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }
    
    
    
}
