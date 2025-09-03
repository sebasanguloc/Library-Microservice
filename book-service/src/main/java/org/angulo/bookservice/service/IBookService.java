package org.angulo.bookservice.service;

import org.angulo.bookservice.model.dto.BookDto;
import org.springframework.data.jpa.repository.Modifying;

import java.util.*;

public interface IBookService {

    /* CRUD */

    // Create
    @Modifying
    BookDto save(BookDto bookDto);

    // Read
    List<BookDto> findAll();

    BookDto findByTitle(String title);

    List<BookDto> findAllByAuthor(String author);

    // Update
    void update(BookDto bookDto);

    void increaseStockByTitle(String title, Integer quantity);

    void decreaseStockByTitle(String title, Integer quantity);

    // Delete
    void removeByTitle(String title);
}
