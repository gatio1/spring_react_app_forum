package com.example.forum.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.forum.Tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.forum.Exceptions.BadInputException;
import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Repository.EntryCollectionRepository;
import com.example.forum.Repository.ForumEntryRepository;
import com.example.forum.Repository.UserRepository;
import com.example.forum.Representation.EntryCollectionRepresentation;
import com.example.forum.Representation.ForumEntryRepresentation;
import com.example.forum.Tables.EntryCollection;
import com.example.forum.Tables.ForumEntry;
import com.example.forum.model.AuthenticatedUser;

@Service
public class ForumEntryService {

    @Autowired
    private ForumEntryRepository forumEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntryCollectionRepository entryCollectionRepository;

    public ForumEntryRepresentation addEntry(ForumEntry forumEntry){
        
        try{
            if(forumEntry.getTitle() == null || forumEntry.getContent() == null){
                throw new BadInputException("Invalid data for entry.");
            }
        }
        catch(BadInputException err){
            throw err;
        }
        forumEntry.setId(null);
        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        forumEntry.setUser(authUser.getUser());
        forumEntryRepository.save(forumEntry);

        ForumEntryRepresentation representation = new ForumEntryRepresentation(forumEntry);
        // forumEntry = forumEntryRepository.findById(forumEntryRepository.count());
        // System.err.println("forum entry title: "+forumEntry.getTitle());

        return representation;
    }

    //Check if User has rights. Also make sure entry is removed from topic and collections and all related comments are removed too.
    public String deleteEntry(Long entryId){
        return null;
    }

    public ForumEntryRepresentation getEntry(Long id)
    {
        ForumEntry entryFound = forumEntryRepository.findById(id);
        
        try{
            if(entryFound == null)
                throw new NotFoundException("Entry doesn't exist.");
        }catch(NotFoundException err)
        {
            throw err;
        }

        return new ForumEntryRepresentation(entryFound);
    }

    public List<ForumEntryRepresentation> getAllEntries(int perPage, int pageNum)
    {
        Iterable<ForumEntry> result = forumEntryRepository.findAll();
        List<ForumEntry> entriesList= new ArrayList<ForumEntry>();
        List<ForumEntryRepresentation> entriesListRep= new ArrayList<ForumEntryRepresentation>();

        result.forEach(entriesList::add);
        entriesList.forEach((n) -> entriesListRep.add(new ForumEntryRepresentation(n)));
        return entriesListRep;
    }

    public List<ForumEntryRepresentation> getByUser()
    {

        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        User user = authUser.getUser();
        List<ForumEntryRepresentation> entriesListRep= new ArrayList<ForumEntryRepresentation>();

        user.getEntries().forEach((n) -> entriesListRep.add(new ForumEntryRepresentation(n)));

        return entriesListRep;
    }

    public List<ForumEntry> getByUserId(long id)
    {
        User user = userRepository.findById(id);
        return user.getEntries();
    }

    public long getNumEntries()
    {
        return forumEntryRepository.count();
    }

    public long getNumEntriesUser(long userId){
        User user = userRepository.findById(userId);
        if(user == null){
            throw new NotFoundException("User not found");
        }

        return user.getEntries().size();
    }

    public List<ForumEntryRepresentation> getCollectionEntries(long collId)
    {
        Set<ForumEntry> entries = null;
        EntryCollection col = entryCollectionRepository.findById(collId);
        if(col == null)
        {
            throw new NotFoundException("Couldn't find collection.");
        }
        List<ForumEntryRepresentation> entryColRep = new ArrayList<ForumEntryRepresentation>();

        entries = col.getForumEntries();
        entries.forEach((n) -> entryColRep.add(new ForumEntryRepresentation(n)));
        
        return entryColRep;
    }

    public List<EntryCollectionRepresentation> setCollections(long entryId, long collectionId)
    {
        ForumEntry entryFound = forumEntryRepository.findById(entryId);
        EntryCollection collFound = entryCollectionRepository.findById(collectionId);
        if(entryFound == null || collFound == null)
        {
            throw new BadInputException("Invalid entry or collection id.");
        }

        entryFound.getEntryCollections().add(collFound);
        forumEntryRepository.save(entryFound);

        Set<EntryCollection> collections = null; 
        List<EntryCollectionRepresentation> colListRep= new ArrayList<EntryCollectionRepresentation>();

        collections = entryFound.getEntryCollections();
        collections.forEach((n) -> colListRep.add(new EntryCollectionRepresentation(n)));

        return colListRep;

    }

    public int getNumPages(int entriesPerPage){
        
        return 0;
    }

    private List<ForumEntry> makePage(int pageNum, int entriesPerPage, List<ForumEntry> allEntries){
        return null;
    }


}
