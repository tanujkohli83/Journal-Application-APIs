package com.tanuj.journalApp.controller;

import com.tanuj.journalApp.entity.JournalEntry;
import com.tanuj.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService JournalEntryService;

    @GetMapping("get-all")
    public List<JournalEntry> getAll() {
        return JournalEntryService.getAll();
    }

    @PostMapping("add-journal")
    public JournalEntry addJournal(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        JournalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalByID(@PathVariable ObjectId myId) {
        return JournalEntryService.findById(myId).orElse(null);

    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntry(@PathVariable ObjectId myId) {
        JournalEntryService.deleteEntry(myId);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateEntrybyId(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        JournalEntry find= JournalEntryService.findById(id).orElse(null);

        if(find != null){
            find.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().isEmpty() ? myEntry.getTitle() : find.getTitle());
            find.setContent(myEntry.getContent() != null && !myEntry.getContent().isEmpty() ? myEntry.getContent() : find.getContent());
        }

        JournalEntryService.saveEntry(find);
        return find;
    }
}
