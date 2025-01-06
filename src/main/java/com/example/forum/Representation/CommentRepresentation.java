package com.example.forum.Representation;

import com.example.forum.Tables.Comment;

public class CommentRepresentation {
    private Long commentId;

    private String commentText;

    private Long commentedEntryId;

    private Long commentingUserId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getCommentedEntryId() {
        return commentedEntryId;
    }

    public void setCommentedEntryId(Long commentedEntryId) {
        this.commentedEntryId = commentedEntryId;
    }

    public Long getCommentingUserId() {
        return commentingUserId;
    }

    public void setCommentingUserId(Long commentingUserId) {
        this.commentingUserId = commentingUserId;
    }

    public CommentRepresentation(Comment comment){
        this.commentId = comment.getCommentId();
        this.commentText = comment.getCommentText();
        this.commentedEntryId = comment.getCommentedEntry().getId();
        this.commentingUserId = comment.getCommentingUser().getId();
    }
}
