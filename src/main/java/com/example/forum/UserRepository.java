package com.example.forum;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.forum.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByUsername(String username);
    List<User> findByEmailAddr(String emailAddr);
}
