package org.angulo.bookservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.angulo.bookservice.Exceptions.InsufficientBooksException;
import org.angulo.bookservice.model.Book;
import org.angulo.bookservice.model.dto.BookDto;
import org.angulo.bookservice.repository.IBookRepository;
import org.angulo.bookservice.service.IBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;

    @Override
    public BookDto save(BookDto bookDto) {
        Boolean existBook = bookRepository.existsByTitle(bookDto.getTitle());
        if(existBook){
            this.increaseStockByTitle(bookDto.getTitle(), bookDto.getStock());
        }else {
            Book book = new Book(
                    bookDto.getTitle(),
                    bookDto.getAuthor(),
                    bookDto.getPrice(),
                    bookDto.getStock()
            );
            Book prBook = bookRepository.save(book);
        }
        Integer stockByTitle = bookRepository.findStockByTitle(bookDto.getTitle());
        bookDto.setStock(stockByTitle);
        return  bookDto;
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAllBooks();
    }

    @Override
    public BookDto findByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<BookDto> findAllByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    public void update(BookDto bookDto) {
    }

    @Override
    public void increaseStockByTitle(String title, Integer quantity) {
        bookRepository.increaseStockByTitle(title,quantity);
    }

    @Override
    public void decreaseStockByTitle(String title, Integer quantity) {
        Integer stockByTitle = bookRepository.findStockByTitle(title);
        if(stockByTitle < quantity){
            throw new InsufficientBooksException("Insufficient number of books");
        }else if(stockByTitle.equals(quantity)) {
            bookRepository.removeBooksByTitle(title);
        }
        bookRepository.decreaseStockByTitle(title,quantity);
    }

    @Override
    public void removeByTitle(String title) {
        bookRepository.removeByTitle(title);
    }
}
