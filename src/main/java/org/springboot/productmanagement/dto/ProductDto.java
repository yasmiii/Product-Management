package org.springboot.productmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String productId;
    private String productName;
    private String description;
    private double price;
    private Integer forSale;
}
