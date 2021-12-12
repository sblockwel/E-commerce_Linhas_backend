package com.lb.ecommerce.controller;

import com.lb.ecommerce.entity.Orders;
import com.lb.ecommerce.models.OrderStatus;
import com.lb.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {


        @Autowired
        private OrdersRepository repository;

        @PostMapping
        public ResponseEntity<Orders> save(@RequestBody Orders orders) {
            Long lastNumber = repository.max();
            orders.setOrderNumber(lastNumber.longValue());
            Orders pS = repository.save(orders);
            return ResponseEntity.ok(pS);
        }

        @GetMapping
        public ResponseEntity<List<Orders>> get(@RequestParam(required = false) OrderStatus status) {
            if (status == null){
                return ResponseEntity.ok(repository.findAll());
            }
            else{
                return ResponseEntity.ok(repository.getByStatus(status));
            }
        }

        @DeleteMapping("/{ordersId}")
        public ResponseEntity delete(@PathVariable("ordersId") long id) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }

}
