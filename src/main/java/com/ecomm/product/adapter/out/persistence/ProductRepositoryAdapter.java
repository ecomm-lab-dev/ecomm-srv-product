package com.ecomm.product.adapter.out.persistence;

import com.ecomm.product.domain.entity.Product;
import com.ecomm.product.domain.exception.SkuAlreadyExistsException;
import com.ecomm.product.infra.persistence.mapper.ProductMapper;
import com.ecomm.product.infra.persistence.repository.JpaProductRepository;
import com.ecomm.product.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Product save(Product product) {
        try {
            jpaProductRepository.saveAndFlush(productMapper.toJpa(product));
            return product;
        } catch (DataIntegrityViolationException exception) {
            String message = exception.getMessage() == null ? "" : exception.getMessage().toLowerCase();
            if (message.contains("uk_products_sku")) {
                log.warn("Product not persisted: sku already registered. productId={}", product.getId());
                throw new SkuAlreadyExistsException();
            }
            log.error("Product not persisted: unexpected integrity violation. productId={}", product.getId(), exception);
            throw exception;
        }
    }
}
