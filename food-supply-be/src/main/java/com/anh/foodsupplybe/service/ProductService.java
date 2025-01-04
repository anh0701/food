package com.anh.foodsupplybe.service;


import com.anh.foodsupplybe.dto.ProductDto;
import com.anh.foodsupplybe.model.Product;
import com.anh.foodsupplybe.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product saveProduct(ProductDto product) {
        Product productSave = new Product();
        productSave.setName(product.getName());
        productSave.setPrice(product.getPrice());
        return productRepository.save(productSave);
    }
    public String deleteProduct(long id) {
        try {
            productRepository.deleteById(id);
            return "Product deleted";
        }catch (Exception e) {
            return "Product not deleted";
        }
    }
    public Product updateProduct(Product product) {
        Product productF = productRepository.findById(product.getId()).get();
        productF.setName(product.getName());
        productF.setPrice(product.getPrice());
        return productRepository.save(productF);
    }
}

