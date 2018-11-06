package com.decathlon.offer.schema.orgpoc.controller;

import com.decathlon.offer.schema.orgpoc.domain.Product;
import com.decathlon.offer.schema.orgpoc.service.ProductService;
import com.google.schemaorg.JsonLdSerializer;
import com.google.schemaorg.JsonLdSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    Map<String, com.google.schemaorg.core.Product> schemaProductMap = new HashMap<>();

    @GetMapping("/product/{sku}")
    public String findProduct(@PathVariable String sku) throws JsonLdSyntaxException {
        String ret = "no product";
        JsonLdSerializer serializer = new JsonLdSerializer(true /* setPrettyPrinting */);
        if(schemaProductMap.get(sku) != null) {
            ret = serializer.serialize(schemaProductMap.get(sku));
            schemaProductMap.remove(sku);
        }
        return ret;
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody Product product) {
        schemaProductMap.put(product.getProductId(), productService.buildProduct(product));
    }
}
