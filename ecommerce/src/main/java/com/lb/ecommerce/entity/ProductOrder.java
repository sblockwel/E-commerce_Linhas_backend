package com.lb.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class ProductOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Product product;

    @ManyToOne
    private Orders orders;

    @NotNull
    @Min(0)
    private int quantity;

    @NotNull
    @Min(0)
    private double price;
}
