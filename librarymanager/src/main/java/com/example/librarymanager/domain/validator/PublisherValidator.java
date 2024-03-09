package com.example.librarymanager.domain.validator;

import com.example.librarymanager.domain.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherValidator implements Validator<Publisher> {
    @Override
    public void validate(Publisher entity) throws ValidationException {
        if (entity.getName() == null || entity.getName().isEmpty()) {
            throw new ValidationException("publisher name cannot be null");
        }
    }
}
