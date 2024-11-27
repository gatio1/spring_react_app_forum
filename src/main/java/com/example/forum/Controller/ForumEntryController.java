package com.example.forum.Controller;

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
    public @ResponseBody String addEntry(@RequestBody ForumEntry forumEntry)
    {

        return null;
    }

    @GetMapping(path="num")    
    public @ResponseBody int getNum()
    {

        return 0;
    }

    @GetMapping(path="/getList") // Truncate entries content
    public @ResponseBody List<ForumEntry> getEntryList(@RequestParam int page, @RequestParam int perPage){
        return null;
    }

    @GetMapping(path="getUsetList")
    public @ResponseBody List<ForumEntry> getUserEntryList(@RequestParam int UserId)
    {
        return null;
    }

    @GetMapping(path="get")
    public @ResponseBody ForumEntry getEntry(@RequestParam int entryId)
    {
        return null;
    }

    @PostMapping(path="/category")
    public @ResponseBody String setCategory(@RequestParam int entryId, @RequestParam int categoryId)
    {

        return "Success";
    }
}
