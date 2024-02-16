package com.cfl.bookmanager.dtos;

import com.cfl.bookmanager.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;

    private String name;

    private String publisher;

    private int year;

    private Float price;

    private String image_id;

    private String author;

    private Integer units;

    public Book createEntity(){
        return new Book(
                this.id,
                this.name,
                this.publisher,
                this.year,
                this.price,
                this.image_id,
                this.author,
                this.units);
    }

}
