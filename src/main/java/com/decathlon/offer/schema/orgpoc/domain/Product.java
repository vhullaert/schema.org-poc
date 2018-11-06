package com.decathlon.offer.schema.orgpoc.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class Product {

    String productId;
    String name;
    String category;
    String brand;
    String description;
    String manufacturer;
}
