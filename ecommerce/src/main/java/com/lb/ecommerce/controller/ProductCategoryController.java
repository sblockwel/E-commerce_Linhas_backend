package com.lb.ecommerce.controller;

import com.lb.ecommerce.data_models.CreateCategoryRequest;
import com.lb.ecommerce.entity.ProductCategory;
import com.lb.ecommerce.repository.ProductCategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryRepository repository;

    private ModelMapper modelMapper;

    public ProductCategoryController() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @PostMapping
    public ResponseEntity<ProductCategory> save(@RequestBody CreateCategoryRequest createCategoryRequest) {
        ProductCategory productCategory = modelMapper.map(createCategoryRequest, ProductCategory.class);
        ProductCategory c = new ProductCategory();
        c.setName(createCategoryRequest.getName());
        c = repository.save(c);
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public List<ProductCategory> findAll()
    {
        return repository.findAll();
    }
}
