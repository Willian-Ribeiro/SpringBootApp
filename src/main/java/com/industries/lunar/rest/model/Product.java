package com.industries.lunar.rest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_PRODUCT")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "QUANTITY_STOCK")
    int quantityStock;

    @Column(name = "QUANTITY_SOLD")
    int quantitySold;

    @Column(name = "QUANTITY_DELIVERING")
    int quantityDelivering;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "IMAGE_URL")
    String imageUrl;

}
