package com.example.forum.Service;


import com.example.forum.Exceptions.BadInputException;
import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Exceptions.UserExistsException;
import com.example.forum.Repository.UserRepository;
import com.example.forum.Representation.UserRepresentation;
import com.example.forum.Tables.User;
import com.example.forum.Tables.UserRole;
import com.example.forum.model.AuthenticatedUser;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Service
public class UserService implements UserDetailsService{
    private UserRepository userRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;  // Dependency Injection
    }

    public UserRepresentation createUser(User user) throws UserExistsException
    {
        List<User> userList;
        if(validateEmail(user.getEmailAddr()) || user.getUsername() == null || user.getPasswordString() == null){
            throw new BadInputException("Invalid user input.");
        }
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

        user.setPasswdHash(passwordEncoder.encode(user.getPasswordString()));

        userRepository.save(user);

        return new UserRepresentation(user);
    }

    
    public UserRepresentation findUser(User user) throws NotFoundException
    {
        System.out.println("email: " + user.getEmailAddr() + ", " + user.getPasswordString());
        List<User> usersFound = userRepository.findByEmailAddr(user.getEmailAddr());
        try{
            if(usersFound.size() == 0)
                throw new NotFoundException("User with given email doesn't exist.");
            if(!passwordEncoder.matches(user.getPasswordString(), usersFound.get(0).getPasswdHash()))
            {
                throw new NotFoundException("User's password doesn't match.");
            }

        }catch(NotFoundException err){
            System.out.println("throwing error " + err.getMessage());
            throw err;
        }

        usersFound.get(0).setPasswdHash(null);
        usersFound.get(0).setPasswordString(null);

        return new UserRepresentation(usersFound.get(0));

    }

    public UserRepresentation getUser(Long id) throws NotFoundException
    {
        User user = userRepository.findById(id);
        try{
            if(user == null)
                throw new NotFoundException("User doesn't exist.");
        }catch(NotFoundException err)
        {
            throw err;
        }

        user.setPasswdHash(null);
        user.setPasswordString(null);;
        return new UserRepresentation(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get(0);
        AuthenticatedUser authUser = new AuthenticatedUser(user);
        return authUser;
    }

    public static boolean validateEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$"; 
        return Pattern.compile(regexPattern)
        .matcher(emailAddress)
        .matches();
    }

}
