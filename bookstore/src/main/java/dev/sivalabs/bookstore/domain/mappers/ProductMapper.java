package dev.sivalabs.bookstore.domain.mappers;

import dev.sivalabs.bookstore.domain.entity.ProductEntity;
import dev.sivalabs.bookstore.domain.model.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto mapToDto(ProductEntity entity) {
        return new ProductDto(
                entity.getCode(), entity.getName(), entity.getDescription(), entity.getImageUrl(), entity.getPrice());
    }
}
