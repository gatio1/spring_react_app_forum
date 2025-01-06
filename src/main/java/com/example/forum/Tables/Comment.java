package com.example.forum.Tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// Comment either of post or of a comment.
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long commentId;

    @Column(length = 512, nullable = false)
    private String CommentText;

    @ManyToOne
    @JoinColumn(name="entry_id")
    private ForumEntry commentedEntry;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User commentingUser;

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setCommentText(String commentText) {
        CommentText = commentText;
    }

    public void setCommentedEntry(ForumEntry commentedEntry) {
        this.commentedEntry = commentedEntry;
    }

    public void setCommentingUser(User commentingUser) {
        this.commentingUser = commentingUser;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getCommentText() {
        return CommentText;
    }

    public ForumEntry getCommentedEntry() {
        return commentedEntry;
    }

    public User getCommentingUser() {
        return commentingUser;
    }
}