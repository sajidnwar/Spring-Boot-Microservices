package com.devil.bookstore.notifications.domain.models;

import com.devil.bookstore.notifications.domain.models.Address;
import com.devil.bookstore.notifications.domain.models.Customer;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderErrorEvent(
        String eventId,
        String orderNumber,
        Set<OrderItem> items,
        Customer customer,
        Address deliveryAddress,
        String reason,
        LocalDateTime createdAt
) {
}
