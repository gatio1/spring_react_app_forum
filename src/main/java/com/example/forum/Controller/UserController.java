package com.example.forum.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Exceptions.UserExistsException;
import com.example.forum.Service.UserService;
import com.example.forum.Tables.User;
import com.example.forum.Tables.UserRole;
import com.example.forum.model.AuthenticatedUser;

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
        try{
        User dbAccessRes = userService.createUser(user);
        }catch(UserExistsException err)
        {
            System.out.println("Error thrown.");
            return err.getMessage();

        }
        return "Saved";
    }

    @GetMapping(path="/authUser") //Spring security takes care of authentication. This function only validates credentials.
    public @ResponseBody User authUser (@RequestBody User user) throws NoSuchAlgorithmException {
        User userInfo = null;
        try{
            userInfo = userService.findUser(user);
        }catch(NotFoundException err)
        {
            throw err;
        }

        return userInfo;
    }

    @GetMapping(path="/getUser")
    public @ResponseBody User getUser (@RequestParam Long id) throws NoSuchAlgorithmException {
        User user = null;
        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        try{
        user = userService.getUser(id);
        }catch(NotFoundException err)
        {
            throw err;
        }
        return user;
    }

}
