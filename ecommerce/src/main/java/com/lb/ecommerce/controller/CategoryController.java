package com.lb.ecommerce.controller;

import com.lb.ecommerce.entity.Category;
import com.lb.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository repository;

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category c = repository.save(category);
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public List<Category> findAll()
    {
        return repository.findAll();
    }
}
