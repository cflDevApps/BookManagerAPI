package com.cfl.bookmanager.services;


import com.cfl.bookmanager.dtos.BookDTO;
import com.cfl.bookmanager.entities.Book;
import com.cfl.bookmanager.exceptions.PersistenceFailException;
import com.cfl.bookmanager.repositories.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    private BookRepository repository;
    @InjectMocks
    private BookService service;

    private BookDTO bookDTO;

    private Book mockedSavedBook;


    @BeforeEach
    public void init(){
        bookDTO = new BookDTO();
        bookDTO.setName("Book DTO");
        bookDTO.setAuthor("Author mocked");
        bookDTO.setPublisher("Publisher mocked");
        bookDTO.setYear(2024);
        bookDTO.setPrice(22.75f);
        bookDTO.setUnits(3);

        mockedSavedBook = bookDTO.createEntity();
        mockedSavedBook.setId(23L);
    }

    @Test
    public void testSaveBookSuccessCallSaveMethodAndReturnBookId(){

        when(repository.save(any(Book.class))).thenReturn(mockedSavedBook);

        Long savedBookId = service.saveBook(this.bookDTO);

        assertEquals(23L, savedBookId);
        verify(this.repository).save(any(Book.class));

    }

    @Test
    public void testSaveBookFailRaisePersistenceFailException(){

        when(repository.save(any(Book.class))).thenReturn(null);

        PersistenceFailException exceptionResponse = assertThrows(PersistenceFailException.class,
                () -> service.saveBook(this.bookDTO));

        assertEquals(exceptionResponse.getMessage(), "New Book not save");
        assertEquals(exceptionResponse.getHttpStatus(), HttpStatus.UNPROCESSABLE_ENTITY);


    }

    @Test
    public void testGetBookByIdSuccessReturnBookEntity(){
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(this.mockedSavedBook));

        Book foundBook = service.getBookById(1L);

        assertEquals(foundBook.getId(), this.mockedSavedBook.getId());
        verify(repository).findById(1L);

    }

}
