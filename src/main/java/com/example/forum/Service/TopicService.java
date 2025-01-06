package com.example.forum.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.forum.Repository.ForumEntryRepository;
import com.example.forum.Repository.TopicRepository;
import com.example.forum.Representation.ForumEntryRepresentation;
import com.example.forum.Representation.TopicRepresentation;
import com.example.forum.Tables.ForumEntry;
import com.example.forum.Tables.Topic;

public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public TopicRepresentation getTopicById(Long topicId)
    {
        Optional<Topic> topicFound = topicRepository.findById(topicId);
        Topic topic = null;
        if(topicFound.isPresent()){
            topic = topicFound.get();
        }
       
        return new TopicRepresentation(topic);
    }

    // Call ForumEntryService remove entry for each related entry.
    //Check if role allows. 
    public String deleteTopic(Long topicId){
        Optional<Topic> topicFound = topicRepository.findById(topicId);
        Topic topic = null;
        if(topicFound.isPresent()){
            topic = topicFound.get();
            topicRepository.delete(topic);
        }

        return "Topic deleted.";
    }

    public List<ForumEntryRepresentation> getTopicEntries(Long topicId){
        Optional<Topic> topicFound = topicRepository.findById(topicId);
        Topic topic = null;
        List<ForumEntryRepresentation> entries = new ArrayList<ForumEntryRepresentation>(); 
        if(topicFound.isPresent()){
            topic = topicFound.get();
            List<ForumEntry> relatedEntries = topic.getAddedPosts();
            for (ForumEntry forumEntry : relatedEntries) {
                entries.add(new ForumEntryRepresentation(forumEntry));
            }
        }
        return entries; 
    }
}
