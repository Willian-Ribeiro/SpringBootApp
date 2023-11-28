package com.industries.lunar.rest.model;

import com.industries.lunar.rest.enums.DeliveryStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "TB_PRODUCT", schema = "public")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(name="seq_tb_product_id", sequenceName="seq_tb_product_id", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "seq_tb_product_id") // use IDENTITY only when you want Hibernate to generate sequence
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY_STOCK")
    private int quantityStock;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "DELIVERY_STATUS", columnDefinition = "ENUM('DISPATCHED', 'DELIVERED')")
    private DeliveryStatusEnum deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "PACKAGE_ID")
    private Package aPackage;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "TB_PRODUCTS_LABELS",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "LABEL_ID"))
    private Set<Label> labels;
}
