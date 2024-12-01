package com.example.forum.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.forum.Tables.ForumEntry;

public interface EntryCollectionRepository extends CrudRepository<ForumEntry, Integer> {

}
