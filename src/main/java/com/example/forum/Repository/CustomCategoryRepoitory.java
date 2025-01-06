package com.example.forum.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.forum.Tables.CustomCategory;

public interface CustomCategoryRepoitory extends CrudRepository<CustomCategory, Long> {

}
