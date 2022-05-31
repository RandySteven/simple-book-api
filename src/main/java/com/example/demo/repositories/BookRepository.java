package com.example.demo.repositories;

import com.example.demo.models.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String>{
    
}
