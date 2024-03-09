package com.example.librarymanager.domain.validator;

import com.example.librarymanager.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator implements Validator<Book>{
    @Override
    public void validate(Book entity) throws ValidationException {
        if (entity.getName() == null || entity.getName().isEmpty()) {
            throw new ValidationException("book name cannot be null");
        }
        if (entity.getAuthor() == null || entity.getAuthor().isEmpty()) {
            throw new ValidationException("book author cannot be null");
        }
    }
}
