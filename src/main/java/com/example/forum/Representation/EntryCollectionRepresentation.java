package com.example.forum.Representation;

import com.example.forum.Tables.EntryCollection;

public class EntryCollectionRepresentation {

    private Long id;

    private String name;

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

    public EntryCollectionRepresentation(EntryCollection entryCollection)
    {
        this.id = entryCollection.getId();
        this.name = entryCollection.getName();
    }

}
