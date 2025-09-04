package org.angulo.orderservice.controller;

import org.angulo.orderservice.model.dto.BookDto;
import org.angulo.orderservice.model.dto.ResponseDto;
import org.angulo.orderservice.service.client.IBookFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Component
public class FallBackRestController implements IBookFeignClient {

    @Override
    public ResponseEntity<ResponseDto<BookDto>> getBookByTitle(String title) {
        return null;
    }

    @Override
    public ResponseEntity<?> decreaseStockOfBooks(String title, Integer quantity) {
        return  null;
    }
}
