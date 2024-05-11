package com.cfl.bookmanager.dtos;

import com.cfl.bookmanager.entities.Book;
import com.cfl.bookmanager.entities.Booking;
import com.cfl.bookmanager.repositories.BookRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingRequestDTO {


    private Long id;
    private List<Long> booksId;
    private LocalDate date;


    public BookingRequestDTO(Long id, @NonNull List<Long> booksId, @NonNull LocalDate date) {
        this.id = id;
        this.booksId = booksId;
        this.date = date;
    }


    public static BookingRequestDTO parseFromEntity(Booking entity){
        BookingRequestDTO dto = new BookingRequestDTO();
        dto.setId(entity.getId());
//        dto.setBooks(entity.getBooks());
        dto.setDate(entity.getDate());
        return dto;
    }

}
