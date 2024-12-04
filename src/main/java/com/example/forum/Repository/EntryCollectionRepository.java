package com.example.forum.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.forum.Tables.EntryCollection;
import com.example.forum.Tables.ForumEntry;

public interface EntryCollectionRepository extends CrudRepository<EntryCollection, Integer> {
    List<EntryCollection> findByName(String name);
    EntryCollection findById(Long id);
}
