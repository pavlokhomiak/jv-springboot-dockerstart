package com.example.jvspringbootdockerstart.service;

import com.example.jvspringbootdockerstart.model.Product;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> findAllSortedByPriceOrTitle(PageRequest pageRequest);
}
