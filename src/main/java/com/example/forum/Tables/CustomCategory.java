package com.example.forum.Tables;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

// A category that only a list of users have access to
@Entity
public class CustomCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long categoryId;

    @Column(unique = true)
    private String categoryName;

    private String categoryDescription;

    @ManyToMany(mappedBy = "customCategories", fetch=FetchType.EAGER)
    private List<ForumEntry> entriesInCategory; 

    @ManyToOne
    @JoinColumn(name="user_id")
    private User categoryOwner;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<ForumEntry> getEntriesInCategory() {
        return entriesInCategory;
    }

    public void setEntriesInCategory(List<ForumEntry> entriesInCategory) {
        this.entriesInCategory = entriesInCategory;
    }

    public User getCategoryOwner() {
        return categoryOwner;
    }

    public void setCategoryOwner(User categoryOwner) {
        this.categoryOwner = categoryOwner;
    }
}
