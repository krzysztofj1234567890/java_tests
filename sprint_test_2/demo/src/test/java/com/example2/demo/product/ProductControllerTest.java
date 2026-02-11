package com.example2.demo.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void shouldCreateProduct() throws Exception {
        // Given
        CreateProductRequest request = new CreateProductRequest("Keyboard", new BigDecimal("99.99"));

        Product product = new Product(
                UUID.randomUUID(),
                "Keyboard",
                new BigDecimal("99.99")
        );

        Mockito.when(productService.create("Keyboard", new BigDecimal("99.99"))).thenReturn(product);

        // When / Then
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(product.getId().toString()))
                .andExpect(jsonPath("$.name").value("Keyboard"))
                .andExpect(jsonPath("$.price").value(99.99));
    }

    @Test
    void shouldReturnAllProducts() throws Exception {
        // Given
        Product product1 = new Product(UUID.randomUUID(), "Keyboard", new BigDecimal("99.99"));
        Product product2 = new Product(UUID.randomUUID(), "Mouse", new BigDecimal("49.99"));

        Mockito.when(productService.findAll()).thenReturn(List.of(product1, product2));

        // When / Then
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Keyboard"))
                .andExpect(jsonPath("$[1].name").value("Mouse"));
    }
}
