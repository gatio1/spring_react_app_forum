package com.example.forum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;  // Dependency Injection
    }

    public User createUser(User user)
    {
        System.out.println(user.toString());
        return user;
    }
    // public UserService(String userJson) // gets User by
    // {
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     try {
    //         // Use ObjectMapper to serialize the User object into a JSON string
    //         user = objectMapper.readValue(userJson, User.class);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public String getJson()
    {
        return "";
    }
}
