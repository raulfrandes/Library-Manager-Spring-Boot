package com.example.librarymanager.service;

import com.example.librarymanager.domain.Book;
import com.example.librarymanager.domain.Publisher;
import com.example.librarymanager.domain.validator.BookValidator;
import com.example.librarymanager.repository.BookRepository;
import com.example.librarymanager.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final BookValidator bookValidator;

    @Autowired
    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository,
                       BookValidator bookValidator) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.bookValidator = bookValidator;
    }

    public Book addBook(Book book) {
        boolean exists = bookRepository.existsById(book.getId());
        if(exists) {
            throw new ServiceException("book with id " + book.getId() + " already exists");
        }
        bookValidator.validate(book);
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book assignPublisherToBook(Long bookId, Long publisherId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ServiceException("book with id " + bookId + " was not found"));
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new ServiceException("publisher with id " + publisherId + " was not found"));
        Set<Publisher> publishers = book.getBookPublishers();
        publishers.add(publisher);
        book.setBookPublishers(publishers);
        return bookRepository.save(book);
    }
}
