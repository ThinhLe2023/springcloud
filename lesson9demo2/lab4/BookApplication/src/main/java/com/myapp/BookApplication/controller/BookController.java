package com.myapp.BookApplication.controller;

import com.myapp.BookApplication.domain.Book;
import com.myapp.BookApplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> retrieveAllBooks() {
        return bookService.getAllBook();
    }

    @GetMapping("/{isbn}")
    public Book retrieveBook(@PathVariable String isbn) {
        return bookService.getBook(isbn);
    }

    @PostMapping
    public ResponseEntity<?> createNewBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);

    }

    @PutMapping("/{isbn}")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }


}
