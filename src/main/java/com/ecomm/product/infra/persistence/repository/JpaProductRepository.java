package com.ecomm.product.infra.persistence.repository;

import com.ecomm.product.infra.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {
}
