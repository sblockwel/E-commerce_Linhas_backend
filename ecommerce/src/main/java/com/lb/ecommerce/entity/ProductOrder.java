package com.lb.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "product_orders")
public class ProductOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Product product;

    @ManyToOne
    private Order order;

    @NotNull
    @Min(0)
    private int quantity;

    @NotNull
    @Min(0)
    private double price;
}
