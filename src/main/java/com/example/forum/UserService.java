package com.example.forum;


import com.example.forum.UserExistsException;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;  // Dependency Injection
    }

    public User createUser(User user) throws UserExistsException
    {
        List<User> userList;
        userList = userRepository.findByEmailAddr(user.getEmailAddr());
        System.out.println("createUser called");
        try{
            if(userList.size() != 0)
            {
                System.out.println("username not unique");
                throw new UserExistsException("Username in use.");
            }

            userList = userRepository.findByUsername(user.getUsername());
            if(userList.size() != 0)
            {
                System.out.println("email not unique");
                throw new UserExistsException("Email in use.");
            }
            System.out.println("Checked uniqueness");
        } catch(UserExistsException err)
        {
            System.out.println("throwing error");
            throw err;
        }

        user.setRole(UserRole.Regular);



        // user.setPasswdHash(user.getPasswordString());
        user.setPasswdHash(new BCryptPasswordEncoder().encode(user.getPasswordString()));
        // user.setPasswdHash(user.getPasswordString());

        userRepository.save(user);

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
