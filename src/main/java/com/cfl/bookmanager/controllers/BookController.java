package com.cfl.bookmanager.controllers;

import com.cfl.bookmanager.services.BookService;
import com.cfl.bookmanager.dtos.BookDTO;
import com.cfl.bookmanager.entities.Book;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final BookService service;
    private final String URI_BASE = "/bookManager/api/book";


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> addBook(@RequestBody BookDTO newBook, UriComponentsBuilder uriBuilder) {
        URI location = uriBuilder.replacePath(String.format("/%s/{id}", URI_BASE))
                .buildAndExpand(service.saveBook(newBook)).toUri();
        return ResponseEntity.created(location).body(location);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String bookInfo){
        return ResponseEntity.ok(service.getBookByFilter(bookInfo));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(service.getAllBooks());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> updateBook(@RequestBody BookDTO newValues, UriComponentsBuilder uriBuilder){
        URI location = uriBuilder.replacePath(String.format("/%s/{id}", URI_BASE))
                .buildAndExpand(service.updateBook(newValues)).toUri();
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}
