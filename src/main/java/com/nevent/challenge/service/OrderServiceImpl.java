package com.nevent.challenge.service;

import com.nevent.challenge.dto.OrderRequest;
import com.nevent.challenge.dto.OrderResponse;
import com.nevent.challenge.entity.Order;
import com.nevent.challenge.entity.Status;
import com.nevent.challenge.exception.InvalidOrderStatusException;
import com.nevent.challenge.exception.OrderNotFoundException;
import com.nevent.challenge.mapper.OrderMapper;
import com.nevent.challenge.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper; // Inyectar el mapper

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = mapper.toEntity(request);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(Status.PENDING);
        Order savedOrder = repository.save(order);
        return mapper.toResponse(savedOrder);
    }
    @Override
    public OrderResponse getOrderById(String id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Page<OrderResponse> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    @Override
    public List<OrderResponse> getOrdersByStatus(String status) {
        Status parsedStatus = parseStatus(status); // Validación de estado
        return repository.findByStatus(parsedStatus.toString())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public OrderResponse updateOrderStatus(String id, Status status) {
        return repository.findById(id)
                .map(order -> {
                    order.setStatus(status);
                    Order updatedOrder = repository.save(order);
                    return mapper.toResponse(updatedOrder);
                })
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public void deleteOrder(String id) {
        if (!repository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        repository.deleteById(id);
    }

    // Método auxiliar para validar el estado
    private Status parseStatus(String status) {
        try {
            return Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidOrderStatusException(status);
        }
    }
}

