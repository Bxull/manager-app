package com.galijz.catalogue.service;

import com.galijz.catalogue.entity.Product;
import com.galijz.catalogue.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> findAllProducts(String filter) {
        if(filter != null && !filter.isBlank()){
            return this.productRepository.findAllByTitleLikeIgnoreCase(filter);
        } else{
            return this.productRepository.findAll();
        }

    }

    @Override
    @Transactional
    public Product createProduct(String title, String details) {
         return this.productRepository.save(new Product(null, title, details));
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        return this.productRepository.findById((long) productId);
    }

    @Override
    @Transactional
    public void updateProduct(Integer id, String title, String details) {
        this.productRepository.findById(Long.valueOf(id))
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);

                    this.productRepository.save(product);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(Long.valueOf(id));
    }
}

