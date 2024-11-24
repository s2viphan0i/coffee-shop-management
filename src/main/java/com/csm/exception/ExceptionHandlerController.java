package com.csm.exception;

import com.csm.factory.ResponseFactory;
import com.csm.model.ResponseStatusEnum;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        JsonMappingException jme = (JsonMappingException) ex.getCause();
        String errorField = jme.getPath().get(jme.getPath().size() - 1).getFieldName();
        log.error("Invalid data type for field [{}]", errorField);
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, ResponseStatusEnum.INVALID_DATA_TYPE, errorField);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<Object> handleValidationException(BadRequestException e) {
        log.error("Handle bad exception", e);
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, e.getErrorStatus().code(), e.getErrorMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception e) {
        log.error("Handle exception", e);
        return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, ResponseStatusEnum.GENERAL_ERROR);
    }
}
