package com.example2.demo.product;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductResponse  create(@RequestBody CreateProductRequest request) {
        Product product = service.create(
            request.name(),
            request.price()
        );
        return ProductMapper.toResponse(product);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.findAll()
            .stream()
            .map(ProductMapper::toResponse)
            .toList();
    }
}
