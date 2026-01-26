package com.devil.bookstore.notifications.domain.models;

import com.devil.bookstore.notifications.domain.models.Address;
import com.devil.bookstore.notifications.domain.models.Customer;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderDeliveredEvent(
        String eventId,
        String orderNumber,
        Set<OrderItem> items,
        Customer customer,
        Address deliveryAddress,
        LocalDateTime createdAt
) {
}
