package com.ecomm.product.port.out;

import com.ecomm.product.domain.entity.Product;

public interface ProductRepositoryPort {
    Product save(Product product);
}
