package com.lb.ecommerce.controller;

import com.lb.ecommerce.entity.Order;
import com.lb.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrdersController {


        @Autowired
        private OrdersRepository repository;

        @PostMapping
        public ResponseEntity<Order> save(@RequestBody Order order) {
            Order pS = repository.save(order);
            return ResponseEntity.ok(pS);
        }

        @GetMapping
        public ResponseEntity<List<Order>> get() {
            return ResponseEntity.ok(repository.findAll());
        }

        @DeleteMapping("/{ordersId}")
        public ResponseEntity delete(@PathVariable("ordersId") long id) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        @GetMapping("/pending")
        public ResponseEntity<List<Order>> getPending() {
        return ResponseEntity.ok(repository.getPending());
    }


}
