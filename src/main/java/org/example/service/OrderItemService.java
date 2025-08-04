package org.example.service;

import org.springframework.stereotype.Service;
import org.example.domain.OrderItem;
import org.example.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem getOrderItemById(String orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElse(null);
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(String orderItemId, OrderItem orderItem) {
        OrderItem existingOrderItem = orderItemRepository.findById(orderItemId).orElse(null);
        if (existingOrderItem != null) {
            existingOrderItem.setQuantity(orderItem.getQuantity());
            existingOrderItem.setMenuItem(orderItem.getMenuItem());
            return orderItemRepository.save(existingOrderItem);
        }
        return null;
    }

    public void deleteOrderItemById(String orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
