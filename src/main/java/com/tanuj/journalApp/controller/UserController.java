package com.tanuj.journalApp.controller;

import com.tanuj.journalApp.entity.JournalEntry;
import com.tanuj.journalApp.entity.User;
import com.tanuj.journalApp.service.JournalEntryService;
import com.tanuj.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User  user){
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<User > updateUser (@RequestBody User user){
        User userInDB = userService.findBuUserName(user.getUserName());
        if(userInDB != null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
