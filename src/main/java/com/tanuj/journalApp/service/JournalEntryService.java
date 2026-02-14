package com.tanuj.journalApp.service;

import com.tanuj.journalApp.entity.JournalEntry;
import com.tanuj.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo JournalEntryRepo;

    public void saveEntry(JournalEntry JournalEntry){

        JournalEntryRepo.save(JournalEntry);
    }

    public List<JournalEntry> getAll(){
        return JournalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return JournalEntryRepo.findById(id);
    }

    public void deleteEntry(ObjectId id){
        JournalEntryRepo.deleteById(id);
    }

}
