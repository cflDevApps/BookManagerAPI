package com.cfl.bookmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {

    private String name;

    private String publisher;

    private Integer year;

    private String author;

}
