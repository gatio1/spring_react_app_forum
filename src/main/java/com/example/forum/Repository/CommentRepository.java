package com.example.forum.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.forum.Tables.Comment;

public interface CommentRepository  extends CrudRepository<Comment, Long>{

}
