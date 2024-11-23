package com.example.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.forum.UserRole;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path="/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;  // Dependency Injection
    }

    @PostMapping(path="/addUser")
    public @ResponseBody String addNewUser (@RequestBody User user) throws NoSuchAlgorithmException {

        System.out.println("User: " + user.getEmailAddr() + ", " + user.getUsername() + ", " + user.getPasswordString());
//        n.setUsername(name);
//        n.setEmailAddr(email);
//        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
//        n.setPasswdHash(digest.digest(passwd.getBytes(StandardCharsets.UTF_8))); // Hash the password
//        n.setRole(UserRole.Regular);
//        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/getUser")
    public String getUser (@RequestParam String json)
    {
        return "null";
    }

}
