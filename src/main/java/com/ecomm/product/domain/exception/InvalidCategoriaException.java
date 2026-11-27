package com.ecomm.product.domain.exception;

import io.grpc.Status;

public class InvalidCategoriaException extends BusinessException {

    private static final String CODE = "INVALID_CATEGORIA";

    public InvalidCategoriaException(String categoria) {
        super(CODE, "Unknown categoria: " + categoria, Status.INVALID_ARGUMENT);
    }
}
