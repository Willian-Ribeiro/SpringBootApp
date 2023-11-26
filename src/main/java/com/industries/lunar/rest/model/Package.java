package com.industries.lunar.rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_PACKAGE", schema = "delivery")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "seq_tb_package_id")
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "aPackage")
    private List<Product> products;

}
