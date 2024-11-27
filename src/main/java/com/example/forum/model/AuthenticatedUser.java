package com.example.forum.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.forum.Tables.User;

public class AuthenticatedUser implements UserDetails{

    private User user;

    public AuthenticatedUser(User user)
    {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getPasswdHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public User getUser(){
        return user;
    }

}
