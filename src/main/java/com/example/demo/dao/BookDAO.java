package com.example.demo.dao;

import java.util.Map;

import com.example.demo.models.Book;

public interface BookDAO {
    
    Map<String, Object> getAllBooks();
    Map<String, Object> getBookById(String id);
    Map<String, Object> postBook(Book book);

}
