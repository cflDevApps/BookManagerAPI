package com.cfl.bookmanager.entities;

import com.cfl.bookmanager.dtos.BookingDTO;
import com.cfl.bookmanager.repositories.BookRepository;
import com.cfl.bookmanager.services.BookService;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @ManyToMany
    private List<Book> books = new ArrayList<Book>();

    @Nonnull
    private LocalDate date;


    public Booking parseFromDto(BookService service, BookingDTO dto){
        this.id = dto.getId();
        this.date = dto.getDate();
        dto.getBooksId().forEach(id -> this.books.add(service.getBookById(id)));
        return this;
    }

}
