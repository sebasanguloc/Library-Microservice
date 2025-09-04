package org.angulo.orderservice.service;

import org.angulo.orderservice.model.dto.OrderDto;
import org.angulo.orderservice.model.dto.OrderRequestDto;

public interface IOrderService {

    public OrderDto createOrder(OrderRequestDto orderDto);

}
