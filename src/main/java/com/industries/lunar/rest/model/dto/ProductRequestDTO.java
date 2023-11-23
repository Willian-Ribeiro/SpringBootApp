package com.industries.lunar.rest.model.dto;

import com.industries.lunar.rest.model.Product;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    String name;
    int quantityStock;
    int quantitySold;
    int quantityDelivering;
    String description;
    String imageUrl;

    Product toProduct()
    {
        return Product.builder()
                .name(this.name)
                .quantityStock(this.quantityStock)
                .quantitySold(this.quantitySold)
                .quantityDelivering(this.quantityDelivering)
                .description(this.description)
                .imageUrl(this.imageUrl)
                .build();
    }
}
