package com.example.commercetools.extension.LineItemValidator;

import com.commercetools.api.models.cart.CartReference;
import com.commercetools.api.models.cart.LineItem;
import com.commercetools.api.models.error.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class LineItemController {

    private static final Logger log = LoggerFactory.getLogger(LineItemController.class);

    @Value("${max.number.of.line.items}")
    private long maxNumberOfLineItems;

    @PostMapping(value = "/validate", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void validate(@RequestBody  final CartReference cart) {
       long totalNumberOfLineItems = cart.getObj().getLineItems().stream().mapToLong(LineItem::getQuantity).sum();
       if(totalNumberOfLineItems > maxNumberOfLineItems) {
           throw new TooManyLineItemsException("too-many-line-items", String.format("Max number of line items: %d, current : %d", maxNumberOfLineItems, totalNumberOfLineItems));
       }
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTooManyLineItems(final TooManyLineItemsException exception) {
        return ErrorResponse.builder().errors(exception).build();
    }
}
