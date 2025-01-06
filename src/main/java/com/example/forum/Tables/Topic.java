package com.example.forum.Tables;

import jakarta.persistence.JoinTable;
// No need because of entry colection
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long topicId;

    private String topicName;
    
    private String topicDesc;

    @ManyToMany    
    @JoinTable(
        name = "TopicSubscribtions",
        joinColumns = @JoinColumn(name = "forumEntry_id"),
        inverseJoinColumns = @JoinColumn(name = "entryCollection_id")
    )
    private Set<User> topicSubscribers = new HashSet<>();

    @OneToMany(fetch=FetchType.EAGER, orphanRemoval=true)
    private List<ForumEntry> addedPosts;

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public void setAddedPosts(List<ForumEntry> addedPosts) {
        this.addedPosts = addedPosts;
    }

    public Long getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public List<ForumEntry> getAddedPosts() {
        return addedPosts;
    }
}
