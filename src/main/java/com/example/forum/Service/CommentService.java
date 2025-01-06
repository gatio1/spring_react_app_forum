package com.example.forum.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Optional;

// import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.forum.Exceptions.BadInputException;
import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Repository.CommentRepository;
import com.example.forum.Repository.ForumEntryRepository;
import com.example.forum.Repository.UserRepository;
import com.example.forum.Representation.CommentRepresentation;
import com.example.forum.Tables.Comment;
import com.example.forum.Tables.ForumEntry;
import com.example.forum.Tables.User;
import com.example.forum.Tables.UserRole;
import com.example.forum.model.AuthenticatedUser;


public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ForumEntryRepository forumEntryRepository;

    @Autowired
    private UserRepository userRepository;

    public CommentRepresentation addNewComment(Long entryId, Comment comment){
        comment.setCommentId(null);
        if(comment.getCommentText() == null){
            throw new BadInputException("Comment has no text.");
        }

        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        User user = authUser.getUser();
        comment.setCommentingUser(user);
        ForumEntry entry = forumEntryRepository.findById(entryId);
        if(entry == null){
            throw new NotFoundException("Couldn't find commented entry.");
        }
        comment.setCommentedEntry(entry);
        
        commentRepository.save(comment);

        return new CommentRepresentation(comment);
    }

    //Check if user has rights.
    public String deleteComment(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){        
            AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
            User user = authUser.getUser();
            List<UserRole> role = new ArrayList<>();
            role.add(UserRole.Admin);
            // Admin and commenter can remove a comment.
            user.roleMatch(role, true, comment.get().getCommentingUser());
            // Author of commented entry ca remove ceomment.
            user.roleMatch(null, true, comment.get().getCommentedEntry().getUser());

            commentRepository.delete(comment.get());
            return "Successfully deleted comment.";
        }
        throw new NotFoundException("Couldn;t find comment.");
    }

    public List<CommentRepresentation> getUserComments(Long userId){
        User user = userRepository.findById(userId);
        if(user == null){
            throw new NotFoundException("Couldn't find user.");
        }
        Set<Comment> comments = user.getUserComments();
        List<CommentRepresentation> commentRepresentations = new ArrayList<>();
        for (Comment comment : comments) {
           commentRepresentations.add(new CommentRepresentation(comment));
        }
        return commentRepresentations;
    }

    public List<CommentRepresentation> getEntryComments(Long entryId){
        ForumEntry entry = forumEntryRepository.findById(entryId);
        if(entry == null){
            throw new NotFoundException("Entry not found.");
        }
        Set<Comment> comments = entry.getEntryComments();

        List<CommentRepresentation> commentRepresentations = new ArrayList<>();
        for (Comment comment : comments) {
           commentRepresentations.add(new CommentRepresentation(comment));
        }
        return commentRepresentations;
    }

}
