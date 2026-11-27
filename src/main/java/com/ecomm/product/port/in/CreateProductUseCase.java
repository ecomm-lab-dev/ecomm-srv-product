package com.ecomm.product.port.in;

import com.ecomm.product.application.dto.CreateProductRequestDomainDTO;
import com.ecomm.product.domain.entity.Product;

public interface CreateProductUseCase {
    Product createProduct(CreateProductRequestDomainDTO request);
}
