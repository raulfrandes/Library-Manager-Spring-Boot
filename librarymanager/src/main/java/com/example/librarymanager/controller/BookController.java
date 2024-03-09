package com.example.librarymanager.controller;

import com.example.librarymanager.domain.Book;
import com.example.librarymanager.service.BookService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}/publisher/{publisherId}")
    public ResponseEntity<Book> assignPublisherToBook(@PathVariable Long bookId, @PathVariable Long publisherId) {
        Book updatedBook = bookService.assignPublisherToBook(bookId, publisherId);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
}
