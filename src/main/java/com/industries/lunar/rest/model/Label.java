package com.industries.lunar.rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "TB_LABEL")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Label {

    @Id
    @SequenceGenerator(name="seq_tb_label_id", sequenceName="seq_tb_label_id", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "seq_tb_label_id")
    private long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "labels")
    private Set<Product> products;

}
