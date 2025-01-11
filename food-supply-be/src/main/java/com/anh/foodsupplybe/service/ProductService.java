package com.anh.foodsupplybe.service;


import com.anh.foodsupplybe.dto.ProductDto;
import com.anh.foodsupplybe.model.Product;
import com.anh.foodsupplybe.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> getProductById(long id);

    List<Product> getAllProducts();

    Product saveProduct(ProductDto product);

    String deleteProduct(long id);

    Product updateProduct(Product product);
}