package com.cfl.bookmanager.dtos;

import com.cfl.bookmanager.entities.Booking;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private List<Long> booksId = new ArrayList<Long>();
    private LocalDate date;


    public BookingDTO(Long id, @NonNull List<Long> booksId, @NonNull LocalDate date) {
        this.id = id;
        this.booksId = booksId;
        this.date = date;
    }


    public BookingDTO parseFromEntity(Booking entity){
        if(entity != null){
            this.id = entity.getId();
            this.date = entity.getDate();
            entity.getBooks().forEach(book -> this.booksId.add(book.getId()));
        }
        return this;
    }

}
