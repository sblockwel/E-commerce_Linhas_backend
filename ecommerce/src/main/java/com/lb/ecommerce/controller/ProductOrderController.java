package com.lb.ecommerce.controller;

import com.lb.ecommerce.entity.Product;
import com.lb.ecommerce.entity.ProductOrder;
import com.lb.ecommerce.repository.ProductOrderRepository;
import com.lb.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productorder")
@CrossOrigin
public class ProductOrderController {

    @Autowired
    private ProductOrderRepository repository;

    @PostMapping
    public ResponseEntity<ProductOrder> save(@RequestBody ProductOrder productorder) {
        ProductOrder pS = repository.save(productorder);
        return ResponseEntity.ok(pS);
    }

    @GetMapping
    public ResponseEntity<List<ProductOrder>> get() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{productorderId}")
    public ResponseEntity delete(@PathVariable("productorderId") long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
