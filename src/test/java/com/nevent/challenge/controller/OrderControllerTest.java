package com.nevent.challenge.controller;

import com.nevent.challenge.dto.OrderRequest;
import com.nevent.challenge.dto.OrderResponse;
import com.nevent.challenge.entity.Status;
import com.nevent.challenge.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService service;

    @InjectMocks
    private OrderController controller;

    @Test
    void testGetOrderById() {
        String orderId = "1";
        OrderResponse orderResponse = new OrderResponse(orderId, "Cliente", LocalDateTime.now(), Status.PENDING, List.of());

        when(service.getOrderById(orderId)).thenReturn(orderResponse);

        ResponseEntity<OrderResponse> response = controller.getOrderById(orderId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderId, Objects.requireNonNull(response.getBody()).id());
    }

    @Test
    void testGetAllOrders() {
        Page<OrderResponse> pageResponse = new PageImpl<>(List.of(new OrderResponse("1", "Cliente", LocalDateTime.now(), Status.PENDING, List.of())));

        when(service.getAllOrders(0, 5)).thenReturn(pageResponse);

        ResponseEntity<Page<OrderResponse>> response = controller.getAllOrders(0, 5);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
    }

    @Test
    void testCreateOrder() {
        OrderRequest request = new OrderRequest("Cliente Test", List.of());
        OrderResponse response = new OrderResponse("1", request.client(), LocalDateTime.now(), Status.PENDING, List.of());

        when(service.createOrder(request)).thenReturn(response);

        ResponseEntity<OrderResponse> result = controller.createOrder(request);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(request.client(), Objects.requireNonNull(result.getBody()).client());
    }
}