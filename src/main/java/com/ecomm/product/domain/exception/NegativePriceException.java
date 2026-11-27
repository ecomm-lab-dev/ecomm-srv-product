package com.ecomm.product.domain.exception;

import io.grpc.Status;

public class NegativePriceException extends BusinessException {

    private static final String CODE = "NEGATIVE_PRICE";
    private static final String MESSAGE = "Price cannot be negative";

    public NegativePriceException() {
        super(CODE, MESSAGE, Status.INVALID_ARGUMENT);
    }
}
