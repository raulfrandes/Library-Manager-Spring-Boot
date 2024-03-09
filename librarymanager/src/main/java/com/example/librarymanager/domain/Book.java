package com.example.librarymanager.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_publishers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private Set<Publisher> bookPublishers = new HashSet<>();

    public Book(String name, String author, Set<Publisher> bookPublishers) {
        this.name = name;
        this.author = author;
        this.bookPublishers = bookPublishers;
    }

    public void assignPublisher(Publisher publisher) {
        bookPublishers.add(publisher);
    }
}
