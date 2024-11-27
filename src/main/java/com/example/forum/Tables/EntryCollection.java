package com.example.forum.Tables;

import java.util.List;

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

    @Column(length = 512, nullable = false)
    private String name;

    @ManyToMany
    private List<ForumEntry> forumEntries;

}
