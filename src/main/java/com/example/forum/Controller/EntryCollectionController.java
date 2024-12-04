package com.example.forum.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.forum.Exceptions.BadInputException;
import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Representation.EntryCollectionRepresentation;
import com.example.forum.Service.EntryCollectionService;
import com.example.forum.Tables.EntryCollection;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/collection")
public class EntryCollectionController {

    @Autowired
    private EntryCollectionService entryCollectionService;

    @GetMapping(path="/getTopics")
    public @ResponseBody List<EntryCollectionRepresentation> getTopicsList()
    {

        return entryCollectionService.getAll();
    }

    @GetMapping(path = "/getEntryCol")
    public @ResponseBody List<EntryCollectionRepresentation> getCollections(long entryId)
    {
        if(entryId > 0)
        {
            return entryCollectionService.getCollectionsForEntry(entryId);
        }else{
            throw new NotFoundException("Entry not found. Can't fetch collections.");
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody EntryCollectionRepresentation addCollection(@RequestBody EntryCollection col) 
    {
        if(col == null ){
            throw new BadInputException("Bad input sent.");
        }
        if(col.getName() == null)
            throw new BadInputException("Bad input sent.");
        System.out.println("Everything's fine.");
        return entryCollectionService.addCollection(col);
    }

}
