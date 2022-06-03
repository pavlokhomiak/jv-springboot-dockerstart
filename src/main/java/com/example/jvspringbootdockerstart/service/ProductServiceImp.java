package com.example.jvspringbootdockerstart.service;

import com.example.jvspringbootdockerstart.model.Product;
import com.example.jvspringbootdockerstart.repository.ProductRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("Product with id %d is not found", id)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAllSortedByPriceOrTitle(PageRequest pageRequest) {
        return repository.findAll(pageRequest).getContent();
    }
}
