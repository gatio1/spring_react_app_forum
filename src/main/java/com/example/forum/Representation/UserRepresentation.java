package com.example.forum.Representation;

import com.example.forum.Tables.User;
import com.example.forum.Tables.UserRole;

public class UserRepresentation {
    private Long id;
    private String username;
    private String emailAddr;
    private String passwdString;
    private UserRole role;
    public String getEmailAddr() {
        return emailAddr;
    }
    public String getPasswdString() {
        return passwdString;
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public UserRole getRole() {
        return role;
    }

    public UserRepresentation(User user)
    {
        this.id = user.getId();
        this.username = user.getUsername();
        this.emailAddr = user.getEmailAddr();
        this.passwdString = user.getPasswordString();
        this.role = user.getRole();
    }
    

}
