package com.csm.exception;

import com.csm.model.ResponseStatusEnum;

public class BadRequestException extends RuntimeException {

    private final ResponseStatusEnum errorStatus;
    private final String errorMessage;

    public BadRequestException(ResponseStatusEnum errorStatus, Object ...errorMessageParams){
        super(String.format(errorStatus.message(), errorMessageParams));
        errorMessage = String.format(errorStatus.message(), errorMessageParams);
        this.errorStatus = errorStatus;
    }

    public ResponseStatusEnum getErrorStatus() {
        return errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
