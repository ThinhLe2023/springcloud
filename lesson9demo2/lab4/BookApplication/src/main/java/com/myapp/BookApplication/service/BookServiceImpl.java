package com.myapp.BookApplication.service;

import com.myapp.BookApplication.domain.Book;
import com.myapp.BookApplication.integration.JmsSender;
import com.myapp.BookApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    JmsSender jmsSender;

    @Override
    public Book addBook(Book book) {
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
        if(existingBook.isPresent()) {
            return existingBook.get();
        }
        jmsSender.sendMessage(book, "addBook");
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        jmsSender.sendMessage(book, "updateBook");
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String isbn) {
        Book removedBook = bookRepository.findByIsbn(isbn).orElse(null);
        jmsSender.sendMessage(removedBook, "deleteBook");
        bookRepository.delete(removedBook);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElse(null);
        return book;
    }
}
