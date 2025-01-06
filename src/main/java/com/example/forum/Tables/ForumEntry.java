package com.example.forum.Tables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;

// A new entry in the forum. Should have relation with User. Can also be a comment to another entry
@Entity
public class ForumEntry {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "entries_collections",
        joinColumns = @JoinColumn(name = "forumEntry_id"),
        inverseJoinColumns = @JoinColumn(name = "entryCollection_id")
    )
    private Set<EntryCollection> entryCollections = new HashSet<>();

    private LocalDateTime timeOfPost;

    public LocalDateTime getTimeOfPost() {
        return timeOfPost;
    }
    public void setTimeOfPost(LocalDateTime timeOfPost) {
        this.timeOfPost = timeOfPost;
    }
    @ManyToMany
    @JoinTable(
        name = "entries_categories",
        joinColumns = @JoinColumn(name = "forumEntry_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CustomCategory> customCategories = new HashSet<>();

    @OneToMany(mappedBy = "commentedEntry")
    private Set<Comment> entryComments = new HashSet<>();

    public Set<Comment> getEntryComments() {
        return entryComments;
    }
    public void setEntryComments(Set<Comment> entryComments) {
        this.entryComments = entryComments;
    }
    @Column(length = 256, nullable = false)
    private String title;

    @Lob
    @Column(length = 10000, nullable = false)
    private String content;

    public Set<EntryCollection> getEntryCollections() {
        return entryCollections;
    }
        public Long getId(){
        return id;
    }
    public void setId( Long id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
}
