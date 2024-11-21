package com.example.forum;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.example.forum.UserRole;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    private String username;

    private String emailAddr;

    private byte[] passwdHash; //Use Spring security

    private UserRole role;


    public Integer getId() {
        return Id;
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

    public byte[] getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(byte[] passwdHash) {
        this.passwdHash = passwdHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


}
