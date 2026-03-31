package dev.sivalabs.bookstore.domain.service;

import dev.sivalabs.bookstore.domain.exception.InvalidOrderException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductServiceClient {
    private final ProductService productService;

    public ProductServiceClient(ProductService productService) {
        this.productService = productService;
    }

    public void validate(String productCode, BigDecimal price) {
        var product = productService
                .getByCode(productCode)
                .orElseThrow(() -> new InvalidOrderException("Product not found with code: " + productCode));
        if (product.price().compareTo(price) != 0) {
            throw new InvalidOrderException("Product price mismatch");
        }
    }
}
