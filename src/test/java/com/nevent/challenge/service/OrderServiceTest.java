package com.nevent.challenge.service;

import com.nevent.challenge.dto.OrderRequest;
import com.nevent.challenge.dto.OrderResponse;
import com.nevent.challenge.entity.Order;
import com.nevent.challenge.entity.Status;
import com.nevent.challenge.mapper.OrderMapper;
import com.nevent.challenge.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.*;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private OrderMapper mapper;

    @InjectMocks
    private OrderServiceImpl service;

    @Test
    void testCreateOrder() {
        OrderRequest request = new OrderRequest("Cliente Test", List.of());
        Order order = new Order();
        OrderResponse response = new OrderResponse("1", "Cliente Test", LocalDateTime.now(), Status.PENDING, List.of());

        when(mapper.toEntity(request)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);
        when(mapper.toResponse(order)).thenReturn(response);

        OrderResponse result = service.createOrder(request);

        assertNotNull(result);
        assertEquals(response.client(), result.client());
        verify(repository, times(1)).save(order);
    }

    @Test
    void testGetOrderById() {
        String orderId = "1";
        Order order = new Order();
        OrderResponse response = new OrderResponse(orderId, "Cliente", LocalDateTime.now(), Status.PENDING, List.of());

        when(repository.findById(orderId)).thenReturn(Optional.of(order));
        when(mapper.toResponse(order)).thenReturn(response);

        OrderResponse result = service.getOrderById(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.id());
    }

    @Test
    void testGetAllOrders() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Order> orders = List.of(new Order(), new Order());
        Page<Order> orderPage = new PageImpl<>(orders);
        Page<OrderResponse> responsePage = orderPage.map(mapper::toResponse);

        when(repository.findAll(pageable)).thenReturn(orderPage);
        when(mapper.toResponse(any())).thenReturn(new OrderResponse("1", "Cliente", LocalDateTime.now(), Status.PENDING, List.of()));

        Page<OrderResponse> result = service.getAllOrders(0, 5);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
    }
}

