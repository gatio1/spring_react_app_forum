package com.example.forum.Controller;

import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Representation.ForumEntryRepresentation;
import com.example.forum.Service.ForumEntryService;
import com.example.forum.Tables.ForumEntry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path="/entry")
public class ForumEntryController {
    
    @Autowired
    ForumEntryService forumEntryService;

    @PostMapping(path="/add")
    public @ResponseBody ForumEntryRepresentation addEntry(@RequestBody ForumEntry forumEntry)
    {
        ForumEntryRepresentation representation = forumEntryService.addEntry(forumEntry);
        return representation;
    }

    @GetMapping(path="/num")    
    public @ResponseBody long getNum()
    {
        return forumEntryService.getNumEntries();
    }

    @GetMapping(path="/numFromUser")
    public @ResponseBody long getNumFromUser(@RequestParam long userId)
    {
        return forumEntryService.getNumEntriesUser(userId);
    }

    @GetMapping(path="/getList") // Truncate entries content
    public @ResponseBody List<ForumEntryRepresentation> getEntryList(@RequestParam int page, @RequestParam int perPage){
        System.out.println("getList called");
        return forumEntryService.getAllEntries(0, 0);
    }

    @GetMapping(path="/getUserList")
    public @ResponseBody List<ForumEntryRepresentation> getUserEntryList()
    {
        return forumEntryService.getByUser();
    }

    @GetMapping(path="/get")
    public @ResponseBody ForumEntryRepresentation getEntry(@RequestParam long entryId)
    {
        ForumEntryRepresentation entry = null;
        try{
            entry = forumEntryService.getEntry(entryId);
        }catch(NotFoundException err){
            throw err;
        }
        return entry;
    }

    @PostMapping(path="/category")
    public @ResponseBody String setCategory(@RequestParam int entryId, @RequestParam int categoryId)
    {

        return "Success";
    }
}
