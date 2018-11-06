package com.decathlon.offer.schema.orgpoc.service;

import com.google.schemaorg.JsonLdFactory;
import com.google.schemaorg.core.CoreFactory;
import com.google.schemaorg.core.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public Product buildProduct(com.decathlon.offer.schema.orgpoc.domain.Product product) {
        //JsonLdSerializer serializer = new JsonLdSerializer(true /* setPrettyPrinting */);
        return
                CoreFactory.newProductBuilder()
                    .addJsonLdContext(
                            JsonLdFactory.newContextBuilder()
                                .setBase("http://www.decathlon.com")
                                .build())
                    .addProductID(product.getProductId())
                    .addName(product.getName())
                    .addDescription(product.getDescription())
                    .addBrand(product.getBrand())
                    .addCategory(product.getCategory())
                    .addManufacturer(product.getManufacturer())
                .build();

    }
}
