package com.example.jvspringbootdockerstart.controller;

import com.example.jvspringbootdockerstart.dto.ProductDtoRequest;
import com.example.jvspringbootdockerstart.dto.ProductDtoResponse;
import com.example.jvspringbootdockerstart.mapper.ProductMapper;
import com.example.jvspringbootdockerstart.model.Product;
import com.example.jvspringbootdockerstart.service.ProductService;
import com.example.jvspringbootdockerstart.util.PageRequestUtil;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductDtoResponse> create(@Valid @RequestBody ProductDtoRequest dto) {
        Product newProduct = mapper.mapToEntity(dto);
        Product product = service.save(newProduct);
        ProductDtoResponse response = mapper.mapToDto(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> get(@PathVariable Long id) {
        Product product = service.getById(id);
        ProductDtoResponse response = mapper.mapToDto(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> update(
            @PathVariable Long id,
            @RequestBody ProductDtoRequest dto) {
        Product product = mapper.mapToEntity(dto).setId(id);
        ProductDtoResponse response = mapper.mapToDto(service.save(product));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> delete(
            @PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> findAllSortedByPriceOrTitle(
            @RequestParam(defaultValue = "20") Integer count,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageRequest = PageRequestUtil.getPageRequest(count, page, sortBy);
        List<ProductDtoResponse> responses = service
                .findAllSortedByPriceOrTitle(pageRequest).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
