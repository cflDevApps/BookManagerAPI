package com.cfl.bookmanager.services;

import com.cfl.bookmanager.dtos.BookDTO;
import com.cfl.bookmanager.dtos.BookingDTO;
import com.cfl.bookmanager.entities.Book;
import com.cfl.bookmanager.exceptions.PersistenceFailException;
import com.cfl.bookmanager.repositories.BookRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {

    @Autowired
    private final BookRepository repository;

    public Book getBookById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Book> getBookByFilter(String bookInfo){
        return repository.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrPublisherContainingIgnoreCase(bookInfo, bookInfo, bookInfo);
    }

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public void deleteBook(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public Long saveBook(@Nonnull BookDTO newBookDto){
        Book newBookEntity = Optional.ofNullable(repository.save(newBookDto.createEntity()))
                .orElseThrow(() -> new PersistenceFailException("New Book not save", HttpStatus.UNPROCESSABLE_ENTITY));
        return newBookEntity.getId();
    }

    public Long updateBook(@Nonnull BookDTO newValues){
        repository.findById(newValues.getId())
                .orElseThrow(() -> new PersistenceFailException(
                        String.format("Book not found for ID: %s", newValues.getId()),
                        HttpStatus.UNPROCESSABLE_ENTITY));

        Book withNewValues = newValues.createEntity();
        repository.saveAndFlush(withNewValues);

        return withNewValues.getId();

    }


    public Long createBooking(BookingDTO dto) {

        return null;
    }
}
