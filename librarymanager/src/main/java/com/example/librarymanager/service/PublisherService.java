package com.example.librarymanager.service;

import com.example.librarymanager.domain.Publisher;
import com.example.librarymanager.domain.validator.PublisherValidator;
import com.example.librarymanager.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherValidator publisherValidator;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository, PublisherValidator publisherValidator) {
        this.publisherRepository = publisherRepository;
        this.publisherValidator = publisherValidator;
    }

    public Publisher addPublisher(Publisher publisher) {
        boolean exists = publisherRepository.existsById(publisher.getId());
        if (exists) {
            throw new ServiceException("publisher with id " + publisher.getId() + " already exists");
        }
        publisherValidator.validate(publisher);
        return publisherRepository.save(publisher);
    }

    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }
}
