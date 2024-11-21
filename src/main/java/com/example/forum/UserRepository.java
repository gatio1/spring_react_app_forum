package com.example.forum;

import org.springframework.data.repository.CrudRepository;
import com.example.forum.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
