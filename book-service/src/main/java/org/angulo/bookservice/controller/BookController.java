package org.angulo.bookservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.angulo.bookservice.model.dto.BookDto;
import org.angulo.bookservice.model.dto.ResponseDto;
import org.angulo.bookservice.service.IBookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:8080")
public class BookController {

    private final IBookService bookService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<BookDto>>> getAllBooks(){
        List<BookDto> books = bookService.findAll();
        ResponseDto<List<BookDto>> response = new ResponseDto<>(
                OK,
                "List of all books",
                books
        );
        return ResponseEntity.status(OK).body(response);
    }

    @GetMapping("/{title}")
    public ResponseEntity<ResponseDto<BookDto>> getBookByTitle(@PathVariable String title){
        BookDto book = bookService.findByTitle(title);
        ResponseDto<BookDto> response = new ResponseDto<>(
            OK,
                "Book by title",
                book
        );
        return ResponseEntity.status(OK).body(response);
    }

    @GetMapping("/author")
    public ResponseEntity<ResponseDto<List<BookDto>>> getAllBooksByAuthor(@RequestParam String name){
        List<BookDto> booksByAuthor = bookService.findAllByAuthor(name);
        ResponseDto<List<BookDto>> response = new ResponseDto<>(
                OK,
                "List of all book by author",
                booksByAuthor
        );
        return ResponseEntity.status(OK).body(response);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDto<BookDto>> registerBook(@RequestBody BookDto bookDto){
        BookDto newBook = bookService.save(bookDto);
        ResponseDto<BookDto> response = new ResponseDto<>(
                CREATED,
                "Book registered successfully",
                newBook
        );
        return ResponseEntity.status(CREATED).body(response);
    }

    @PatchMapping("/lend")
    public ResponseEntity<?> decreaseStockOfBooks(@RequestParam String title, @RequestParam Integer quantity){
        bookService.decreaseStockByTitle(title,quantity);
        BookDto bookUpdated = bookService.findByTitle(title);
        ResponseDto<?> response = new ResponseDto<>(
                OK,
                "Books leaded successfully",
                bookUpdated
        );
        return  ResponseEntity.status(OK).body(response);
    }
}
