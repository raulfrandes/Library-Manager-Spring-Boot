package com.example.librarymanager.repository;

import com.example.librarymanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
