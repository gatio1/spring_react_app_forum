package com.example.forum.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forum.Exceptions.BadInputException;
import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Repository.EntryCollectionRepository;
import com.example.forum.Repository.ForumEntryRepository;
import com.example.forum.Tables.EntryCollection;
import com.example.forum.Tables.ForumEntry;
import com.example.forum.Representation.EntryCollectionRepresentation;
import com.example.forum.Representation.ForumEntryRepresentation;

@Service
public class EntryCollectionService {
    @Autowired
    private EntryCollectionRepository entryCollectionRepository;

    @Autowired
    private ForumEntryRepository forumEntryRepository;

    public List<EntryCollectionRepresentation> getAll()
    {
        Iterable<EntryCollection> col = entryCollectionRepository.findAll();
        List<EntryCollection> colList= new ArrayList<EntryCollection>();
        List<EntryCollectionRepresentation> colListRep= new ArrayList<EntryCollectionRepresentation>();

        col.forEach(colList::add);
        colList.forEach((n) -> colListRep.add(new EntryCollectionRepresentation(n)));
        System.out.println("Returning all collections.(Service)");
        return colListRep;
    }

    public EntryCollectionRepresentation addCollection(EntryCollection entryCollection)
    {
        List<EntryCollection> colList = entryCollectionRepository.findByName((entryCollection.getName()));
        if(colList.size() != 0)
        {
            System.out.println("Request is bad. size of col: " + colList.size());
            throw new BadInputException("Collection exists.");
        }

        entryCollection.setId(null);

        entryCollectionRepository.save(entryCollection);
        System.out.println("Added collection.");

        return new EntryCollectionRepresentation(entryCollection);
    }

    public List<EntryCollectionRepresentation> getCollectionsForEntry(long entryId)
    {
        Set<EntryCollection> collections = null; 
        ForumEntry entry = forumEntryRepository.findById(entryId);
        List<EntryCollectionRepresentation> colListRep= new ArrayList<EntryCollectionRepresentation>();

        collections = entry.getEntryCollections();
        collections.forEach((n) -> colListRep.add(new EntryCollectionRepresentation(n)));

        return colListRep;
    }

}
