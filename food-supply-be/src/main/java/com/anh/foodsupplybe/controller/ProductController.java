package com.anh.foodsupplybe.controller;

import com.anh.foodsupplybe.dto.ProductDto;
import com.anh.foodsupplybe.model.Product;
import com.anh.foodsupplybe.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("get-all")
//    @PreAuthorize("hasAuthority('PERMISSION_READ_PRODUCT')")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getAllProducts();
        if (products == null || products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        products.forEach(product -> System.out.println("Product: " + product.getName() + "Id = " + product.getId()));
        return ResponseEntity.ok(products);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('PERMISSION_ADD_PRODUCT')")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto product) {
        Product savedProduct = productService.saveProduct(product);
        logger.error("method add running..................{}", savedProduct.getName());
        if (savedProduct == null) {
            Product newProduct = new Product("No content");
            return ResponseEntity.ok(newProduct);
        }
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('PERMISSION_UPDATE_PRODUCT')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product savedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_DELETE_PRODUCT')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String product =  productService.deleteProduct(id);
        return ResponseEntity.ok(product);
    }
}

