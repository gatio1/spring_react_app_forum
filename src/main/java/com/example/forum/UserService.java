package com.example.forum;


import com.fasterxml.jackson.databind.ObjectMapper;

public class UserService {
    private User user;
    private String json_out;
    public UserService(User user)
    {

    }

    public UserService(String userJson) // gets User by
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Use ObjectMapper to serialize the User object into a JSON string
            user = objectMapper.readValue(userJson, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJson()
    {
        return "";
    }
}
