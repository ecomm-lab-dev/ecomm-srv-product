package com.ecomm.product.infra.persistence.entity;

import com.ecomm.product.domain.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Product")
@Table(name = "products", uniqueConstraints = @UniqueConstraint(name = "uk_products_sku", columnNames = "sku"))
public class ProductEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private LocalDate inclusionDate;
    private boolean isActive;
}
