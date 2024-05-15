package com.cfl.bookmanager.services;

import com.cfl.bookmanager.dtos.BookingDTO;
import com.cfl.bookmanager.dtos.BookingResponseDTO;
import com.cfl.bookmanager.entities.Book;
import com.cfl.bookmanager.entities.Booking;
import com.cfl.bookmanager.repositories.BookRepository;
import com.cfl.bookmanager.repositories.BookingRepository;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {


    @Autowired
    private BookingRepository repo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper mapper;

    public Long createBooking(@Nonnull BookingDTO dto) {
        Booking booking = new Booking().parseFromDto(bookService, dto);
        booking = repo.save(booking);
        return booking.getId();

    }
    public List<BookingResponseDTO> getAllBooking() {
        List<Booking> bookings = repo.findAll();
        return bookings.stream().map(booking ->mapper.map(booking, BookingResponseDTO.class)).toList();
    }

    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = repo.findById(id).orElse(null);
        return mapper.map(booking, BookingResponseDTO.class);
    }
}
