package com.tanuj.journalApp.service;

import com.tanuj.journalApp.entity.JournalEntry;
import com.tanuj.journalApp.entity.User;
import com.tanuj.journalApp.repository.JournalEntryRepo;
import com.tanuj.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo UserRepo;

    public void saveEntry(User user){
        UserRepo.save(user);
    }

    public List<User> getAll(){
        return UserRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return UserRepo.findById(id);
    }

    public void deleteEntry(ObjectId id){
        UserRepo.deleteById(id);
    }

}
