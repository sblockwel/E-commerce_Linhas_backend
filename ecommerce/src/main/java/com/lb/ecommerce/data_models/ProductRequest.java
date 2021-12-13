package com.lb.ecommerce.data_models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class ProductRequest {
    private final String name;
    private final String description;
    private final Double price;
    private final int quantity;
    private final int categoryId;
}
