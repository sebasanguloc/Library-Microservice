package org.angulo.orderservice.controller;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    @PostMapping
   public ResponseEntity<ResponseDto<OrderDto>> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderDto order = orderService.createOrder(orderRequestDto);
        ResponseDto<OrderDto> response = new ResponseDto<>(
                HttpStatus.CREATED,
                "Order created successfully",
                order
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
