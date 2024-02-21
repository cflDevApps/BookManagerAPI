package com.cfl.bookmanager.dtos;

import com.cfl.bookmanager.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;


public class BookDTOTest {

    @Test
    public void testBookDTOConvertionToEntity(){
        BookDTO dto = new BookDTO(1L, "book name", "publisher name", 2021, 23.75f, null, "author name", 5);
        Book entity = dto.createEntity();

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getPublisher(), dto.getPublisher());
        assertEquals(entity.getYear(), dto.getYear());
        assertEquals(entity.getPrice(), dto.getPrice());
        assertEquals(entity.getAuthor(), dto.getAuthor());
        assertNull(entity.getImageId());

    }

}
