package com.example.forum.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.forum.Tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Repository.ForumEntryRepository;
import com.example.forum.Repository.UserRepository;
import com.example.forum.Tables.ForumEntry;
import com.example.forum.model.AuthenticatedUser;

@Service
public class ForumEntryService {

    @Autowired
    private ForumEntryRepository forumEntryRepository;

    @Autowired
    private UserRepository userRepository;

    public ForumEntry getEntry(Long id)
    {
        ForumEntry entryFound = forumEntryRepository.findById(id);
        
        try{
            if(entryFound == null)
                throw new NotFoundException("Entry doesn't exist.");
        }catch(NotFoundException err)
        {
            throw err;
        }

        return entryFound;
    }

    public List<ForumEntry> getAllEntries(int perPage, int pageNum)
    {
        Iterable<ForumEntry> result = forumEntryRepository.findAll();
        List<ForumEntry> entriesList= new ArrayList<ForumEntry>();

        result.forEach(entriesList::add);
        return entriesList;
    }

    public List<ForumEntry> getByUser()
    {

        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        User user = authUser.getUser();

        return user.getEntries();
    }

    public List<ForumEntry> getByUserId(long id)
    {
        User user = userRepository.findById(id);
        return user.getEntries();
    }

}
