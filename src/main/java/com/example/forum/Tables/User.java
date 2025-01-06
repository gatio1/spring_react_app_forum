package com.example.forum.Tables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER, orphanRemoval = true)
    private List<ForumEntry> entries;

    @OneToMany(mappedBy = "user1", fetch=FetchType.EAGER, orphanRemoval = true)
    private List<UserRelation> RelationEntriesFrom;

    @OneToMany(mappedBy = "user2", fetch=FetchType.EAGER, orphanRemoval = true)
    private List<UserRelation> RelationEntriesTo;

    @ManyToMany(mappedBy = "collectionSubscribers")
    private Set<EntryCollection> subscribedCollections = new HashSet<>();

    @ManyToMany(mappedBy = "topicSubscribers")
    private Set<Topic> subscribedTopics = new HashSet<>();

    @OneToMany(mappedBy = "categoryOwner", orphanRemoval = true)
    private Set<CustomCategory> ownedCategories = new HashSet<>();

    @OneToMany(mappedBy = "commentingUser")
    private Set<Comment> userComments = new HashSet<>();

    public Set<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(Set<Comment> userComments) {
        this.userComments = userComments;
    }

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
