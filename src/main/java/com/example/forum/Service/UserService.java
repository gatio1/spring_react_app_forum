package com.example.forum.Service;


import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Exceptions.UserExistsException;
import com.example.forum.Repository.UserRepository;
import com.example.forum.Tables.User;
import com.example.forum.Tables.UserRole;
import com.example.forum.model.AuthenticatedUser;

import java.util.List;

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

        user.setPasswdHash(passwordEncoder.encode(user.getPasswordString()));

        userRepository.save(user);

        return user;
    }

    
    public User findUser(User user) throws NotFoundException
    {
        List<User> usersFound = userRepository.findByEmailAddr(user.getEmailAddr());
        try{
            if(usersFound.size() == 0)
                throw new NotFoundException("User with given email doesn't exist.");
            user.setPasswdHash(passwordEncoder.encode(user.getPasswordString()));
            if(usersFound.get(0).getPasswdHash() != user.getPasswdHash())
            {
                throw new NotFoundException("User's password doesn't match.");
            }

        }catch(NotFoundException err){
            System.out.println("throwing error");
            throw err;
        }

        usersFound.get(0).setPasswdHash(null);
        usersFound.get(0).setPasswordString(null);

        return usersFound.get(0);

    }

    public User getUser(Long id) throws NotFoundException
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
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get(0);
        AuthenticatedUser authUser = new AuthenticatedUser(user);
        return authUser;
    }

}
