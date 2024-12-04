package com.example.forum.Tables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

// A list of articles with similar topics. can be many to many

@Entity
public class EntryCollection {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 256, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "entryCollections")
    private Set<ForumEntry> forumEntries = new HashSet<>();

    public Set<ForumEntry> getForumEntries() {
        return forumEntries;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}
