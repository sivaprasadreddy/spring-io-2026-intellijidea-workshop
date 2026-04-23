package dev.sivalabs.bookstore.domain.service;

import dev.sivalabs.bookstore.domain.mappers.ProductMapper;
import dev.sivalabs.bookstore.domain.model.PagedResult;
import dev.sivalabs.bookstore.domain.model.ProductDto;
import dev.sivalabs.bookstore.domain.repo.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private static final int PRODUCT_PAGE_SIZE = 10;
    private final ProductRepository repo;
    private final ProductMapper productMapper;

    ProductService(ProductRepository repo, ProductMapper productMapper) {
        this.repo = repo;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public PagedResult<ProductDto> getProducts(int pageNo) {
        log.info("Fetching products for page: {}", pageNo);
        Sort sort = Sort.by("name").ascending();
        int page = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(page, PRODUCT_PAGE_SIZE, sort);
        Page<ProductDto> productsPage = repo.findAll(pageable).map(productMapper::mapToDto);
        return new PagedResult<>(productsPage);
    }

    @Transactional(readOnly = true)
    @Cacheable("product-by-code")
    public Optional<ProductDto> getByCode(String code) {
        log.info("Fetching product by code: {}", code);
        return repo.findByCode(code).map(productMapper::mapToDto);
    }
}
