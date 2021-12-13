package com.lb.ecommerce.data_models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private int categoryId;
}
