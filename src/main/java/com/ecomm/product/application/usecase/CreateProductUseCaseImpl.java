package com.ecomm.product.application.usecase;

import com.ecomm.product.application.dto.CreateProductRequestDomainDTO;
import com.ecomm.product.domain.entity.Product;
import com.ecomm.product.domain.exception.InvalidRequestException;
import com.ecomm.product.port.in.CreateProductUseCase;
import com.ecomm.product.port.out.ProductRepositoryPort;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final Validator validator;
    private final ProductRepositoryPort productRepository;

    @Override
    public Product createProduct(CreateProductRequestDomainDTO request) {

        log.info("Creating product");
        validate(request);

        Product product = Product.createProduct(
                request.name(),
                request.description(),
                request.sku(),
                request.price(),
                request.categoria()
        );
        Product savedProduct = productRepository.save(product);
        log.info("Product created. productId={}", savedProduct.getId());
        return savedProduct;
    }

    private void validate(CreateProductRequestDomainDTO command) {
        var message = validator.validate(command).stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .sorted()
                .collect(Collectors.joining("; "));
        if (!message.isEmpty()) {
            log.warn("Product creation rejected: validation failed. violations={}", message);
            throw new InvalidRequestException(message);
        }
    }
}
