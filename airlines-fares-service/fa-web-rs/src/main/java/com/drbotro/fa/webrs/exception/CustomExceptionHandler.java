package com.drbotro.fa.webrs.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.drbotro.fa.common.exception.ApiError;
import com.drbotro.fa.common.exception.EntityConflictException;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    private static final String ERROR = "ERROR";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<GenericResponseFareWebResponse> handleAllExceptions(Exception ex, WebRequest request){

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .withDescription("Server Error").withDetails(Arrays.asList(ex.getMessage())).build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityConflictException.class)
    public final ResponseEntity<GenericResponseFareWebResponse> handleUserNotFoundException(EntityConflictException ex,
            WebRequest request){

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.CONFLICT.value()).withDescription("Record Exists")
                        .withDetails(Arrays.asList(ex.getLocalizedMessage())).build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request){

        List<String> details = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        ApiError error = ApiError.builder().withCode(HttpStatus.BAD_REQUEST.value())
                .withDescription("Validation Failed").withDetails(details).build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
