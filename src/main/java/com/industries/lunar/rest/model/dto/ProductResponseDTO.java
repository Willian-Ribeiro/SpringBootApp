package com.industries.lunar.rest.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.industries.lunar.rest.enums.DeliveryStatusEnum;
import com.industries.lunar.rest.model.Label;
import com.industries.lunar.rest.model.Package;
import com.industries.lunar.rest.model.Product;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private long id;
    private String name;
    private int quantityStock;
    private String description;
    private DeliveryStatusEnum deliveryStatus;
    @JsonIgnoreProperties({"products"})
    private Package aPackage;
    @JsonIgnoreProperties({"products"})
    private Set<Label> labels;

    public ProductResponseDTO(Product product)
    {
        this.id = product.getId();
        this.name = product.getName();
        this.quantityStock = product.getQuantityStock();
        this.description = product.getDescription();
        this.deliveryStatus = product.getDeliveryStatus();
        this.aPackage = product.getAPackage();
        this.labels = product.getLabels();
    }

    public static Page<ProductResponseDTO> toDTO(Page<Product> products)
    {
        List<ProductResponseDTO> list = products.map(ProductResponseDTO::new).getContent();
        return new PageImpl<>(list);
    }
}
