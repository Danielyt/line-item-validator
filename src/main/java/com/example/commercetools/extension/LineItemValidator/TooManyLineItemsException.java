package com.example.commercetools.extension.LineItemValidator;

import com.commercetools.api.models.error.ErrorObject;

public class TooManyLineItemsException extends RuntimeException {

    public TooManyLineItemsException(final String message) {
        super(message);
    }
}
