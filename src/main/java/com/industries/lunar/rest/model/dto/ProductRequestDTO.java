package com.industries.lunar.rest.model.dto;

import com.industries.lunar.rest.enums.DeliveryStatusEnum;
import com.industries.lunar.rest.model.Product;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String name;
    private int quantityStock;
    private String description;
    private DeliveryStatusEnum deliveryStatus;

    public Product toProduct()
    {
        return Product.builder()
                .name(this.name)
                .quantityStock(this.quantityStock)
                .description(this.description)
                .deliveryStatus(this.deliveryStatus)
                .build();
    }
}
