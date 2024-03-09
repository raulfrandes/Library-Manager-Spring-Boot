package com.example.librarymanager.controller;

import com.example.librarymanager.domain.Publisher;
import com.example.librarymanager.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publishers = publisherService.findAllPublishers();
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
        Publisher newPublisher = publisherService.addPublisher(publisher);
        return new ResponseEntity<>(newPublisher, HttpStatus.CREATED);
    }
}
