package com.nevent.challenge.mapper;

import com.nevent.challenge.dto.ItemRequest;
import com.nevent.challenge.dto.ItemResponse;
import com.nevent.challenge.dto.OrderRequest;
import com.nevent.challenge.dto.OrderResponse;
import com.nevent.challenge.entity.Item;
import com.nevent.challenge.entity.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class OrderMapper {
    public Order toEntity(OrderRequest request) {
        Order order = new Order();
        order.setClient(request.client());
        order.setItems(request.items().stream()
                .map(this::toItemEntity)
                .toList());
        return order;
    }

    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getClient(),
                order.getCreatedAt(),
                order.getStatus(),
                order.getItems().stream()
                        .map(this::toItemResponse)
                        .toList()
        );
    }

    private Item toItemEntity(ItemRequest request) {
        Item item = new Item();
        item.setProduct(request.product());
        item.setQuantity(request.quantity());
        item.setUnitPrice(request.unitPrice());
        return item;
    }

    private ItemResponse toItemResponse(Item item) {
        BigDecimal subtotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
        return new ItemResponse(
                item.getProduct(),
                item.getQuantity(),
                item.getUnitPrice()
        );
    }
}