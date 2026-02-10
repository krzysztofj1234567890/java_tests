package com.example2.demo.product;

import java.math.BigDecimal;

public record CreateProductRequest(String name, BigDecimal price) {
}
