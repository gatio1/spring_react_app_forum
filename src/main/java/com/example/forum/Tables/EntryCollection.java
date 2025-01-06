package com.example.forum.Tables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

// A list of articles with similar topics. can be many to many

@Entity
public class EntryCollection {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 256, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "entryCollections", fetch=FetchType.EAGER)
    private Set<ForumEntry> forumEntries = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "collections_users",
        joinColumns = @JoinColumn(name = "entry_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> collectionSubscribers = new HashSet<>();

    public Set<User> getCollectionSubscribers() {
        return collectionSubscribers;
    }

    public void setCollectionSubscribers(Set<User> collectionSubscribers) {
        this.collectionSubscribers = collectionSubscribers;
    }

    public void setForumEntries(Set<ForumEntry> forumEntries) {
        this.forumEntries = forumEntries;
    }

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
