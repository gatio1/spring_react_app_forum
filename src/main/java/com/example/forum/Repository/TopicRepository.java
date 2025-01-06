package com.example.forum.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.forum.Tables.Topic;

public interface TopicRepository  extends CrudRepository<Topic, Long>{
}
