package com.lb.ecommerce.controller;

import com.lb.ecommerce.entity.Order;
import com.lb.ecommerce.models.OrderStatus;
import com.lb.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersController {


        @Autowired
        private OrdersRepository repository;

        @PostMapping
        public ResponseEntity<Order> save(@RequestBody Order order) {
            Long lastNumber = repository.max();
            order.setOrderNumber(lastNumber.longValue());
            Order pS = repository.save(order);
            return ResponseEntity.ok(pS);
        }

        @GetMapping
        public ResponseEntity<List<Order>> get(@RequestParam(required = false) OrderStatus status, @RequestParam(required = false) String user) {
            if (status != null){
                return ResponseEntity.ok(repository.getByStatus(status));
            }
            else if (user != null){
                return ResponseEntity.ok(repository.getByUser(user));
            }
            else{
                return ResponseEntity.ok(repository.findAll());
            }
        }

        @DeleteMapping("/{ordersId}")
        public ResponseEntity delete(@PathVariable("ordersId") long id) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }

}
