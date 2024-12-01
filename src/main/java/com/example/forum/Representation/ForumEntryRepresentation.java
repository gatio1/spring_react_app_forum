package com.example.forum.Representation;

import com.example.forum.Tables.ForumEntry;

public class ForumEntryRepresentation {
    private Long id;

    private String title;

    private String content;

    private String username;

    private Long userId;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
   public ForumEntryRepresentation(ForumEntry forumEntry)
   {
        this.setId(forumEntry.getId());
        this.setContent(forumEntry.getContent());
        this.setTitle(forumEntry.getTitle());
        this.setUserId(forumEntry.getUser().getId());
        this.setUsername(forumEntry.getUser().getUsername());

   } 

}
