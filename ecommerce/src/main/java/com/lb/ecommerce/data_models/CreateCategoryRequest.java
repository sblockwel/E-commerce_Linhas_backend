package com.lb.ecommerce.data_models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class CreateCategoryRequest {
    private String name;
}
