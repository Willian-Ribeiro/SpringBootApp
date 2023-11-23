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

    @Column(name = "quantityStock")
    int quantityStock;

    @Column(name = "NAME")
    int quantitySold;

    @Column(name = "NAME")
    int quantityDelivering;

    @Column(name = "NAME")
    String description;

    @Column(name = "NAME")
    String imageUrl;

}
