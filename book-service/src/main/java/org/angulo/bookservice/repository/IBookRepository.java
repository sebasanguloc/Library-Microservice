package org.angulo.bookservice.repository;

import org.angulo.bookservice.model.Book;
import org.angulo.bookservice.model.dto.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {

    /* CRUD */

    // Read
    @Query("SELECT new org.angulo.bookservice.model.dto.BookDto(b.title, b.author, b.price, b.stock) FROM Book b")
    List<BookDto> findAllBooks();

    @Query("SELECT new org.angulo.bookservice.model.dto.BookDto(b.title, b.author, b.price, b.stock) FROM Book b WHERE b.title = :title")
    Optional<BookDto> findByTitle(@Param("title") String title);

    @Query("SELECT new org.angulo.bookservice.model.dto.BookDto(b.title, b.author, b.price, b.stock) FROM Book b WHERE b.author = :author")
    List<BookDto> findAllByAuthor(@Param("author") String author);

    @Query("SELECT b.stock FROM Book b WHERE b.title = :title")
    Integer findStockByTitle(@Param("title") String title);

    Boolean existsByTitle(String title);

    // Update
    @Modifying
    @Query("UPDATE Book b SET  b.stock = b.stock + :quantity WHERE b.title = :title")
    void increaseStockByTitle(@Param("title") String title, @Param("quantity") Integer quantity);

    @Modifying
    @Query("UPDATE Book b SET  b.stock = b.stock - :quantity WHERE b.title = :title")
    void decreaseStockByTitle(@Param("title") String title, @Param("quantity") Integer quantity);

    void removeByTitle(String title);

    void removeBooksByTitle(String title);
}
