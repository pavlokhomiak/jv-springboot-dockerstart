package com.example.jvspringbootdockerstart.mapper;

import com.example.jvspringbootdockerstart.dto.ProductDtoRequest;
import com.example.jvspringbootdockerstart.dto.ProductDtoResponse;
import com.example.jvspringbootdockerstart.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDtoResponse mapToDto(Product product) {
        return new ProductDtoResponse()
            .setTitle(product.getTitle())
            .setPrice(product.getPrice());
    }

    public Product mapToEntity(ProductDtoRequest dto) {
        return new Product()
            .setTitle(dto.getTitle())
            .setPrice(dto.getPrice());
    }
}
