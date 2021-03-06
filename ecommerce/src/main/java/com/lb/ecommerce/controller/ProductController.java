package com.lb.ecommerce.controller;

import com.lb.ecommerce.data_models.ProductRequest;
import com.lb.ecommerce.entity.Product;
import com.lb.ecommerce.entity.ProductCategory;
import com.lb.ecommerce.repository.ProductCategoryRepository;
import com.lb.ecommerce.repository.ProductRepository;
import com.lb.ecommerce.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity save(@RequestBody ProductRequest productRequest) {
        Product product = MapperUtils.map(productRequest, Product.class);
        if (!categoryRepository.existsById(productRequest.getCategoryId())) {
            return ResponseEntity.badRequest().body("Essa categoria não existe ou está vazia!");
        }
        ProductCategory productCategory = categoryRepository.getById(productRequest.getCategoryId());
        product.setCategory(productCategory);
        product = repository.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> get() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity delete(@PathVariable("productId") long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
