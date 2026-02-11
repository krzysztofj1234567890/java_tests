package com.example2.demo.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test") // use H2 in-memory DB
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Save and find a product")
    void testSaveAndFind() {
        Product product = new Product(UUID.randomUUID(), "Keyboard", new BigDecimal("99.99"));
        productRepository.save(product);

        List<Product> all = productRepository.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getName()).isEqualTo("Keyboard");
        assertThat(all.get(0).getPrice()).isEqualByComparingTo("99.99");
    }

    @Test
    @DisplayName("Update a product")
    void testUpdate() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Keyboard", new BigDecimal("99.99"));
        productRepository.save(product);

        // fetch and update
        Product existing = productRepository.findById(id).orElseThrow();
        Product updated = new Product(existing.getId(), existing.getName(), new BigDecimal("79.99"));
        productRepository.save(updated);

        Optional<Product> result = productRepository.findById(id);
        assertThat(result).isPresent();
        assertThat(result.get().getPrice()).isEqualByComparingTo("79.99");
    }

    @Test
    @DisplayName("Delete a product")
    void testDelete() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Mouse", new BigDecimal("49.99"));
        productRepository.save(product);

        productRepository.deleteById(id);

        Optional<Product> result = productRepository.findById(id);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Find all products")
    void testFindAll() {
        Product p1 = new Product(UUID.randomUUID(), "Keyboard", new BigDecimal("99.99"));
        Product p2 = new Product(UUID.randomUUID(), "Mouse", new BigDecimal("49.99"));
        productRepository.saveAll(List.of(p1, p2));

        List<Product> all = productRepository.findAll();
        assertThat(all).hasSize(2);
        assertThat(all).extracting(Product::getName).containsExactlyInAnyOrder("Keyboard", "Mouse");
    }
}
