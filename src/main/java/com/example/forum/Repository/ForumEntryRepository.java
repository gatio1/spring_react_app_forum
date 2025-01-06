package com.example.forum.Repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.forum.Tables.ForumEntry;

public interface ForumEntryRepository extends CrudRepository<ForumEntry, Integer> {
    ForumEntry findById(Long id);
    // @Query(value = "SELECT id, title, SUBSTRING(content, 1, 50) AS content FROM ForumENtry;",
    // countQuery = "SELECT count(id, title, SUBSTRING(content, 1, 50) AS content) FROM ForumENtry;")
    // Page<ForumEntry> getEntriesTrunc(Pageable pageable);
}
