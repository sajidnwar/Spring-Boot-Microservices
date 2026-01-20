package com.sajid.bookstore.orders.domain.models;

public record OrderSummary(
        String orderNumber, OrderStatus status
) {
}
