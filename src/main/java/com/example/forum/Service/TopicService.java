package com.example.forum.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.forum.Exceptions.BadInputException;
import com.example.forum.Repository.ForumEntryRepository;
import com.example.forum.Repository.TopicRepository;
import com.example.forum.Representation.ForumEntryRepresentation;
import com.example.forum.Representation.TopicRepresentation;
import com.example.forum.Tables.ForumEntry;
import com.example.forum.Tables.Topic;
import com.example.forum.Tables.User;
import com.example.forum.Tables.UserRole;
import com.example.forum.model.AuthenticatedUser;

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

    public TopicRepresentation addTopic(Topic topic){
        topic.setTopicId(null);
        if(topic.getTopicName() == null){
            throw new BadInputException("Topic name not specified.");
        }

        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        User user = authUser.getUser();
        List<UserRole> role = new ArrayList<>();
        role.add(UserRole.Admin);
        user.roleMatch(role, false, null);

        topicRepository.save(topic);

        return new TopicRepresentation(topic);
    }

    public String deleteTopic(Long topicId){

        Optional<Topic> topicFound = topicRepository.findById(topicId);
        Topic topic = null;
        if(topicFound.isPresent()){
            topic = topicFound.get();
            AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
            User user = authUser.getUser();
            List<UserRole> role = new ArrayList<>();
            role.add(UserRole.Admin);
            user.roleMatch(role, false, null);

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
