package com.example.commercetools.extension.LineItemValidator;

import com.commercetools.api.models.error.ErrorObject;

public class TooManyLineItemsException extends RuntimeException implements ErrorObject {

    private String code;

    public TooManyLineItemsException(final String code, final String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setMessage(String s) {
        // intentionally left empty
    }
}
