package com.lb.ecommerce.controller;

import com.lb.ecommerce.entity.ProductCategory;
import com.lb.ecommerce.entity.Product;
import com.lb.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


        @Autowired
        private ProductRepository repository;

        @PostMapping
        public ResponseEntity<Product> save(@RequestBody Product product) {
            Product pS = repository.save(product);
            return ResponseEntity.ok(pS);
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
