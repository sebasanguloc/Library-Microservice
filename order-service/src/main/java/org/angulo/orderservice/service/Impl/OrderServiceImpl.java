package org.angulo.orderservice.service.Impl;

import lombok.RequiredArgsConstructor;
import org.angulo.orderservice.Exception.InsufficientBooksException;
import org.angulo.orderservice.constant.Status;
import org.angulo.orderservice.model.Order;
import org.angulo.orderservice.model.OrderItem;
import org.angulo.orderservice.model.dto.*;
import org.angulo.orderservice.repository.IOrderRepository;
import org.angulo.orderservice.service.IOrderService;
import org.angulo.orderservice.service.client.IBookFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {

    private final IBookFeignClient bookFeignClient;
    private final IOrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderRequestDto orderRequestDto) {
        List<BookOrderDto> booksOrder = orderRequestDto.getItems();

        booksOrder.forEach(bookOrderDto -> {
            String title = bookOrderDto.getTitle();
            Integer quantity = bookOrderDto.getQuantity();

            ResponseEntity<ResponseDto<BookDto>> bookResponse = bookFeignClient.getBookByTitle(title);
            Integer stock = bookResponse.getBody().getData().getStock();
            if(stock < quantity) throw new InsufficientBooksException("Insufficient number of books");

            bookFeignClient.decreaseStockOfBooks(title,quantity);
        });
        Order order = new Order(
                orderRequestDto.getUserEmail(),
                Status.PENDING.toString()
        );
        Order newOrder = orderRepository.save(order);


        OrderItem orderItem = new OrderItem();

        return new OrderDto(
                newOrder.getUserEmail(),
                newOrder.getStatus(),
                LocalDateTime.now()
        );
    }

}
