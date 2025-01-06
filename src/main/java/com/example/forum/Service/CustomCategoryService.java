package com.example.forum.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.forum.Exceptions.NotFoundException;
import com.example.forum.Repository.CustomCategoryRepoitory;
import com.example.forum.Repository.ForumEntryRepository;
import com.example.forum.Representation.CustomCategoryRepresentation;
import com.example.forum.Representation.ForumEntryRepresentation;
import com.example.forum.Tables.CustomCategory;
import com.example.forum.Tables.ForumEntry;
import com.example.forum.Tables.User;
import com.example.forum.Tables.UserRole;
import com.example.forum.model.AuthenticatedUser;

@Service
public class CustomCategoryService {
    @Autowired
    private ForumEntryRepository forumEntryRepository;
    @Autowired
    private CustomCategoryRepoitory customCategoryRepository;

    //Should make the relation with a user.
    public CustomCategoryRepresentation createNewCategory(CustomCategory customCategory)
    {
        Optional<CustomCategory> category = customCategoryRepository.findById(customCategory.getCategoryId());
        if(category.isPresent()){
            throw new NotFoundException("Category alerady exists.");
        }

        customCategoryRepository.save(customCategory);
        return new CustomCategoryRepresentation(customCategory);
    }

    //Remove just the category. Check if user role allows.
    public CustomCategoryRepresentation deleteCategory(Long categoryId)
    {
        Optional<CustomCategory> category = customCategoryRepository.findById(categoryId);

        if(category.isPresent())
        {
            customCategoryRepository.delete(category.get()); 
            AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
            User user = authUser.getUser();
            List<UserRole> role = new ArrayList<>();
            user.roleMatch(null, true, category.get().getCategoryOwner());

            return new CustomCategoryRepresentation(category.get());
        }
        throw new NotFoundException("Couldn't find category to delete.");
    }

    public CustomCategoryRepresentation addEntryToCategory(Long categoryId, Long entryId)
    {
        Optional<CustomCategory> categoryFound = customCategoryRepository.findById(categoryId);
        if(!categoryFound.isPresent())
        {
            throw new NotFoundException("Didn't find category");
        }
        ForumEntry entryFound = forumEntryRepository.findById(entryId);
        CustomCategory category = categoryFound.get();
        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        User user = authUser.getUser();
        user.roleMatch(null, true, category.getCategoryOwner());

        category.getEntriesInCategory().add(entryFound);

        customCategoryRepository.save(category);
        return new CustomCategoryRepresentation(category);

    }

    public List<ForumEntry> getAllCategoryEntries(Long categoryId)
    {

        Optional<CustomCategory> categoryFound = customCategoryRepository.findById(categoryId);
        if(!categoryFound.isPresent())
        {
            throw new NotFoundException("Didn't find category");
        }
        CustomCategory category = categoryFound.get();

        AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Get user credentials trough spring security.
        User user = authUser.getUser();
        user.roleMatch(null, true, category.getCategoryOwner());

        List<ForumEntry> entries = category.getEntriesInCategory();
        List<ForumEntryRepresentation> representations = new ArrayList<>();
        for (ForumEntry forumEntry : entries) {
            representations.add(new ForumEntryRepresentation(forumEntry));
        }

        return entries;
    }


}
