package com.tanuj.journalApp.repository;

import com.tanuj.journalApp.entity.JournalEntry;
import com.tanuj.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {

}
