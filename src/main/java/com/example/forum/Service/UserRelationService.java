package com.example.forum.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Repository.UserRelationRepository;
import com.example.forum.Repository.UserRepository;
import com.example.forum.Representation.UserRepresentation;
import com.example.forum.Tables.User;
import com.example.forum.Tables.UserRelation;
import com.example.forum.model.AuthenticatedUser;

public class UserRelationService {
    @Autowired 
    private UserRelationRepository userRelationRepository;

    @Autowired 
    private UserRepository userRepository;

    //Check if user has rights.
    public String addRelation(Long user2Id){
        UserRelation relation = new UserRelation();
        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        User user2Found = userRepository.findById(user2Id);
        if(user2Found == null){
            throw new NotFoundException("Couldn't find user.");
        }

        User fetchedUser = authUser.getUser();
        relation.setUser1(fetchedUser);
        relation.setUser2(user2Found);
        if(fetchedUser == null){
            throw new NotFoundException("Authenticated user not found.");
        }
        userRelationRepository.save(relation);
        return null;
    }

    //Check user privileges.
    public String removeRelation(Long relationId){
        Optional<UserRelation> relationFound = userRelationRepository.findById(relationId);
        if(!relationFound.isPresent()){
            throw new NotFoundException("Couldn't find relation");
        }
        UserRelation Relation = relationFound.get();

        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        User myUser = authUser.getUser();
        myUser.roleMatch(null, true, relationFound.get().getUser1());
        

        userRelationRepository.delete(relationFound.get());
        return "Successfully deleted relation.";
    }

    public List<UserRepresentation> getRelatedUsers(Long userId){
        List<UserRelation> userRelations = userRelationRepository.findByUser1Id(userId);
        if(userRelations == null){
            throw new NotFoundException("Relation not found.");
        }
        userRelations.addAll(userRelationRepository.findByUser2Id(userId));
        List<UserRepresentation> users = new ArrayList<>();
        for (UserRelation userRelation : userRelations) {
            if(userRelation.getUser1().getId() != userId){
                users.add(new UserRepresentation(userRelation.getUser1()));
            }else{
                users.add(new UserRepresentation(userRelation.getUser2()));
            }
        }
        return users;
    }

    //Being user2 in relation means being followed.
    public List<UserRepresentation> getRelatedUsers1(Long userId){
        List<UserRelation> userRelations = userRelationRepository.findByUser2Id(userId);
        if(userRelations == null){
            throw new NotFoundException("Relation not found.");
        }
        List<UserRepresentation> users = new ArrayList<>();
        for (UserRelation userRelation : userRelations) {
            users.add(new UserRepresentation(userRelation.getUser1()));
        }
 
        return users;
    }

    // Being User 1 in relation means follow
    public List<UserRepresentation> getRelatedUsers2(Long userId){

        List<UserRelation> userRelations = userRelationRepository.findByUser1Id(userId);
        if(userRelations == null){
            throw new NotFoundException("Relation not found.");
        }
        List<UserRepresentation> users = new ArrayList<>();
        for (UserRelation userRelation : userRelations) {
            users.add(new UserRepresentation(userRelation.getUser2()));
        }
 
        return users;
    }

}
