package com.cfl.bookmanager.services;

import com.cfl.bookmanager.dtos.BookDTO;
import com.cfl.bookmanager.entities.Book;
import com.cfl.bookmanager.repositories.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {

    @Autowired
    private final BookRepository repository;

    public Long saveBook(BookDTO newBookDto){
        Book newBookEntity = Optional.of(repository.save(newBookDto.createEntity()))
                .orElseThrow(() -> new RuntimeException("New Book not save"));
        return newBookEntity.getId();
    }

    public Book getBookById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Book> getBookByFilter(String bookInfo){
        return repository.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublisherContainingIgnoreCase(bookInfo, bookInfo, bookInfo);
    }

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Long updateBook(BookDTO newValues){
        repository.findById(newValues.getId())
                .orElseThrow(() -> new RuntimeException(String.format("Book not found for ID: %s", newValues.getId())));

        Book withNewValues = newValues.createEntity();
        repository.saveAndFlush(withNewValues);

        return withNewValues.getId();

    }

    public void deleteBook(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

}
