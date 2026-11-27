package com.ecomm.product.infra.persistence.mapper;

import com.ecomm.product.domain.entity.Product;
import com.ecomm.product.infra.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toJpa(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .price(product.getPrice())
                .categoria(product.getCategoria())
                .inclusionDate(product.getInclusionDate())
                .isActive(product.isActive())
                .build();
    }
}
