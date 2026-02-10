package com.example2.demo.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductResponse(
    UUID id,
    String name,
    BigDecimal price,
    Instant createdAt
) {
}
