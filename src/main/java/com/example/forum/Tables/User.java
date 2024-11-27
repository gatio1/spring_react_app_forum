package com.example.forum.Tables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

// Make another field to let a user follow others.
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String username;

    private String emailAddr;

    private String passwdHash; //Use Spring security

    @Transient // Added to send the password string in request body and not as parameter. Not present in db.
    private String passwordString;

    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<ForumEntry> entries;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPasswordString() {
        return passwordString;
    }

    public void setPasswordString(String passwordString){
        this.passwordString = passwordString;
    }

    public List<ForumEntry> getEntries(){
        return entries;
    }


}
