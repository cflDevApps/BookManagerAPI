package com.cfl.bookmanager.controllers;

import com.cfl.bookmanager.dtos.BookingDTO;
import com.cfl.bookmanager.dtos.BookingResponseDTO;
import com.cfl.bookmanager.entities.Book;
import com.cfl.bookmanager.services.BookingService;
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
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private final BookingService bookingService;

    private final String URI_BASE = "/bookManager/api/booking";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> addBooking(@RequestBody BookingDTO bookingDTO, UriComponentsBuilder uriBuilder){
        URI location = uriBuilder.replacePath(String.format("/%s/{id}", URI_BASE))
                .buildAndExpand(bookingService.createBooking(bookingDTO)).toUri();
        return ResponseEntity.created(location).body(location);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookingResponseDTO>> getAll(){
        return ResponseEntity.ok(bookingService.getAllBooking());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingResponseDTO> getBooking(@PathVariable("id") Long id){
        BookingResponseDTO bookingById = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingById);
    }

}
