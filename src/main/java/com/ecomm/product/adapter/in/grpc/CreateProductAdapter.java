package com.ecomm.product.adapter.in.grpc;

import com.ecomm.product.application.dto.CreateProductRequestDomainDTO;
import com.ecomm.product.domain.enums.Categoria;
import com.ecomm.product.domain.exception.BusinessException;
import com.ecomm.product.port.in.CreateProductUseCase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;
import product.CreateProductGrpc;
import product.CreateProductRequest;
import product.CreateProductResponse;

import java.math.BigDecimal;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class CreateProductAdapter extends CreateProductGrpc.CreateProductImplBase {

    private final CreateProductUseCase createProductUseCase;

    @Override
    public void createProduct(CreateProductRequest req, StreamObserver<CreateProductResponse> obs) {

        try {
            log.info("gRPC createProduct received");
            var product = createProductUseCase.createProduct(toCommand(req));
            log.info("gRPC createProduct completed. productId={}", product.getId());

            obs.onNext(CreateProductResponse.newBuilder()
                    .setId(product.getId().toString())
                    .setName(product.getName())
                    .setDescription(product.getDescription() == null ? "" : product.getDescription())
                    .setSku(product.getSku())
                    .setPrice(product.getPrice().doubleValue())
                    .setCategoria(product.getCategoria().name())
                    .build());
            obs.onCompleted();
        } catch (BusinessException exception) {
            log.warn("Business error creating product. code={}, message={}", exception.getCode(), exception.getMessage());
            obs.onError(exception.getGrpcStatus()
                    .withDescription(exception.getMessage())
                    .asRuntimeException());
        }
    }

    private CreateProductRequestDomainDTO toCommand(CreateProductRequest req) {
        return new CreateProductRequestDomainDTO(
                req.getName(),
                req.getDescription(),
                req.getSku(),
                BigDecimal.valueOf(req.getPrice()),
                Categoria.fromString(req.getCategoria())
        );
    }
}
