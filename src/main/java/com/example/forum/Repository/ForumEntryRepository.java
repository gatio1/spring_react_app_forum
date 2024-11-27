package com.example.forum.Repository;

import org.springframework.data.repository.CrudRepository;
import com.example.forum.Tables.ForumEntry;

public interface ForumEntryRepository  extends CrudRepository<ForumEntry, Integer> {
    ForumEntry findById(Long id);
}
