package com.example.forum.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forum.Repository.EntryCollectionRepository;

@Service
public class EntryCollectionService {
    @Autowired
    EntryCollectionRepository entryCollectionRepository;

}
