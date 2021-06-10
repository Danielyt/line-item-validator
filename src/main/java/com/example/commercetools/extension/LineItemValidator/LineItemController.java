package com.example.commercetools.extension.LineItemValidator;

import com.example.commercetools.extension.LineItemValidator.model.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineItemController {

    private static final Logger log = LoggerFactory.getLogger(LineItemController.class);

    @PostMapping(value = "/validate", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void validate(final Request request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Received request: " + objectMapper.writeValueAsString(request));
    }
}
