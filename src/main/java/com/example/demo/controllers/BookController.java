package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.dao.BookDAO;
import com.example.demo.dto.BookDTO;
import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/books")
public class BookController implements BookDAO{
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String GET_ALL_BOOKS_ENDPOINT = "/get-all-books";
    private static final String POST_BOOK_ENDPOINT = "/add-book";
    private static final String GET_BOOK_BY_ID_ENDPOINT = "/{id}";

    private Map<String, Object> responseMap;

    private List<Book> books;

    @Override
    @GetMapping(GET_ALL_BOOKS_ENDPOINT)
    public Map<String, Object> getAllBooks() {
        responseMap = new HashMap<>();

        Query query = new Query();
        query.addCriteria(Criteria.where("deletedAt").isNull());

        books = mongoTemplate.find(query, Book.class);
        
        if(books.isEmpty()){
            responseMap.put("responseCode", 200);
            responseMap.put("responseMessage", "Book still empty");            
        }else{
            List<BookDTO> bookDTOs = new ArrayList<>();
            books.forEach(book -> bookDTOs.add(new BookDTO(book)));
            responseMap.put("responseCode", 200);
            responseMap.put("responseMessage", "Get all books");
            responseMap.put("totals", bookDTOs.size());
            responseMap.put("books", bookDTOs);    
        }

        return responseMap;
    }

    @Override
    @GetMapping(GET_BOOK_BY_ID_ENDPOINT)
    public Map<String, Object> getBookById(@PathVariable String id) {
        responseMap = new HashMap<>();

        Book book = bookRepository.findById(id).get();

        if(book != null){
            if(book.getDeletedAt() == null){
                responseMap.put("book", book);
                responseMap.put("responseCode", 200);
                responseMap.put("responseMessage", "Get Book");
            }else{
                responseMap.put("responseCode", 404);
                responseMap.put("responseMessage", "Book is not found :'(");
            }
        }else{
            responseMap.put("responseCode", 404);
            responseMap.put("responseMessage", "Book is not found :'(");
        }

        return responseMap;
    }

    @Override
    @PostMapping(value = POST_BOOK_ENDPOINT, consumes = MediaType.ALL_VALUE)
    public Map<String, Object> postBook(@RequestBody Book book) {

        responseMap = new HashMap<>();

        Book addBook = bookRepository.insert(book);
        addBook.setCreatedAt(LocalDateTime.now());
        addBook.setDeletedAt(null);
        addBook.setUpdatedAt(null);

        responseMap.put("book", addBook);
        responseMap.put("resultStatus", "S");
        responseMap.put("statusCode", 201);
        responseMap.put("responseMessage", "Post Book success");

        return responseMap;
    }

}
