package org.angulo.orderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.angulo.orderservice.model.dto.OrderDto;
import org.angulo.orderservice.model.dto.OrderRequestDto;
import org.angulo.orderservice.model.dto.ResponseDto;
import org.angulo.orderservice.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDto<OrderDto>> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        log.debug("B - CREATING ORDER - ORDER_SERVICE -");
        OrderDto order = orderService.createOrder(orderRequestDto);
        ResponseDto<OrderDto> response = new ResponseDto<>(
                HttpStatus.CREATED,
                "Order created successfully",
                order
        );
        log.debug("E - CREATING ORDER - ORDER_SERVICE -");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
