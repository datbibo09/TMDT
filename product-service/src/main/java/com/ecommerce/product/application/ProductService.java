package com.ecommerce.product.application;

import com.ecommerce.product.domain.exception.ProductNotFoundException;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Transactional
    public Product create(Product product) {
        product.validate();
        productRepository.persist(product);
        return product;
    }

    public List<Product> findAll() {
        return productRepository.listAll();
    }

    public Product findById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    @Transactional
    public Product update(Long id, Product newData) {
        Product existing = productRepository.findById(id);

        if (existing == null) {
            throw new ProductNotFoundException(id);
        }

        // Entity sẽ tự throw InvalidProductName/Price/StockException nếu dữ liệu không hợp lệ
        existing.update(
                newData.getName(),
                newData.getPrice(),
                newData.getAvailableQuantity()
        );

        return existing;
    }

    @Transactional
    public Product delete(Long id) {
        Product existing = productRepository.findById(id);
        if (existing == null) {
            throw new ProductNotFoundException(id);
        }
        productRepository.delete(existing);
        return existing;
    }
}