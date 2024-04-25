package com.myapp.BookApplication.service;

import com.myapp.BookApplication.domain.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(String isbn);

    List<Book> getAllBook();

    Book getBook(String isbn);



}
