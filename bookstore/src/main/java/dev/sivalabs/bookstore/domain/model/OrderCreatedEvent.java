package dev.sivalabs.bookstore.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderNumber,
        BigDecimal totalAmount,
        Customer customer) {
}
