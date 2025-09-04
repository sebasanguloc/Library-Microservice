package org.angulo.orderservice.service.client;

import org.angulo.orderservice.model.dto.BookDto;
import org.angulo.orderservice.model.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("BOOK-SERVICE")
public interface IBookFeignClient {

    @GetMapping(value = "/books/{title}", consumes = "application/json")
    public ResponseEntity<ResponseDto<BookDto>> getBookByTitle(@PathVariable String title);

    @PutMapping("/books/lend")
    public ResponseEntity<?> decreaseStockOfBooks(@RequestParam String title, @RequestParam Integer quantity);

}
