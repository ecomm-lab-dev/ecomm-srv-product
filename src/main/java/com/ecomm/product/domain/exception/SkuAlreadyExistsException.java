package com.ecomm.product.domain.exception;

import io.grpc.Status;

public class SkuAlreadyExistsException extends BusinessException {

    private static final String CODE = "SKU_ALREADY_EXISTS";
    private static final String MESSAGE = "SKU already registered";

    public SkuAlreadyExistsException() {
        super(CODE, MESSAGE, Status.ALREADY_EXISTS);
    }
}
