package com.example.demo.dto;

import com.example.demo.models.Book;

public class BookDTO {

    private static final String BASIC_URL = "http://localhost:8080/books/";

    private String bookTitle;
    private String bookGenre;
    private String bookAuthor;
    private String hateoasURL;

    public BookDTO(Book book){
        this.bookTitle = book.getBookTitle();
        this.bookGenre = book.getBookGenre();
        this.bookAuthor = book.getBookAuthor();
        this.hateoasURL = BASIC_URL + book.getId();
    }

    public static String getBasicUrl() {
        return BASIC_URL;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getHateoasURL() {
        return hateoasURL;
    }

    public void setHateoasURL(String hateoasURL) {
        this.hateoasURL = hateoasURL;
    }
}
