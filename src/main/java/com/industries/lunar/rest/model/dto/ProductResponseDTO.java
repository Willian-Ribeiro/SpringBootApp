package com.industries.lunar.rest.model.dto;

import com.industries.lunar.rest.model.Product;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    long id;
    String name;
    int quantityStock;
    int quantitySold;
    int quantityDelivering;
    String description;
    String imageUrl;

    public ProductResponseDTO(Product product)
    {
        this.id = product.getId();
        this.name = product.getName();
        this.quantityStock = product.getQuantityStock();
        this.quantitySold = product.getQuantitySold();
        this.quantityDelivering = product.getQuantityDelivering();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
    }

    public static Page<ProductResponseDTO> toDTO(Page<Product> products)
    {
        List<ProductResponseDTO> list = products.map(ProductResponseDTO::new).getContent();
        return new PageImpl<>(list);
    }
}
