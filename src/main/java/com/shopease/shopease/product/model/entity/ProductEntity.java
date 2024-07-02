package com.shopease.shopease.product.model.entity;

import com.shopease.shopease.common.entity.BaseEntity;
import com.shopease.shopease.product.model.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private Long stock;

    @Enumerated(EnumType.STRING)
    private Category category;

}
