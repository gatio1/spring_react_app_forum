package com.example.forum.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.forum.Tables.UserRelation;

import com.example.forum.Tables.User;


public interface UserRelationRepository extends CrudRepository<UserRelation, Long> { 

    public List<UserRelation> findByUser1Id(Long user1Id);

    public List<UserRelation> findByUser2Id(Long user2Id);

}
