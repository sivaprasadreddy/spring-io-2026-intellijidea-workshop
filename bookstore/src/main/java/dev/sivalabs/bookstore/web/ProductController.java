package dev.sivalabs.bookstore.web;

import dev.sivalabs.bookstore.domain.exception.ProductNotFoundException;
import dev.sivalabs.bookstore.domain.model.PagedResult;
import dev.sivalabs.bookstore.domain.model.ProductDto;
import dev.sivalabs.bookstore.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<ProductDto> getProducts(@RequestParam(defaultValue = "1") int page) {
        log.info("Fetching products for page: {}", page);
        return productService.getProducts(page);
    }

    @GetMapping("/{code}")
    ProductDto getProductByCode(@PathVariable String code) {
        log.info("Fetching product by code: {}", code);
        return productService
                .getByCode(code)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
