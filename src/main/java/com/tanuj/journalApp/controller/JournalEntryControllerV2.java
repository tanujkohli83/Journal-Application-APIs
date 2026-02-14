package com.tanuj.journalApp.controller;

import com.tanuj.journalApp.entity.JournalEntry;
import com.tanuj.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService JournalEntryService;

    @GetMapping("get-all")
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> JournalEntry = JournalEntryService.getAll();
        if(JournalEntry != null && !JournalEntry.isEmpty()){
            return new ResponseEntity<>(JournalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("add-journal")
    public ResponseEntity<JournalEntry> addJournal(@RequestBody JournalEntry myEntry) {

        try {
            myEntry.setDate(LocalDateTime.now());
            JournalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalByID(@PathVariable ObjectId myId) {
        Optional<JournalEntry> JournalEntry = JournalEntryService.findById(myId);

        if (JournalEntry.isPresent()) {
            return new ResponseEntity<>(JournalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<JournalEntry> deleteEntry(@PathVariable ObjectId myId) {
        Optional<JournalEntry> JournalEntry = JournalEntryService.findById(myId);

        if(JournalEntry.isPresent()){
            JournalEntryService.deleteEntry(myId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateEntrybyId(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        JournalEntry find = JournalEntryService.findById(id).orElse(null);

        if (find != null) {
            find.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().isEmpty() ? myEntry.getTitle() : find.getTitle());
            find.setContent(myEntry.getContent() != null && !myEntry.getContent().isEmpty() ? myEntry.getContent() : find.getContent());
            JournalEntryService.saveEntry(find);
            return new ResponseEntity<>(find,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
