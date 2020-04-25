package com.drbotro.fa.webrs.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.drbotro.fa.common.exception.ApiError;
import com.drbotro.fa.common.exception.AuthorizationTokenException;
import com.drbotro.fa.common.exception.CustomUsernameNotFoundException;
import com.drbotro.fa.common.exception.EntityFareConflictException;
import com.drbotro.fa.common.exception.EntityFareNotFoundException;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    private static final String ERROR = "KO";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<GenericResponseFareWebResponse> handleAllExceptions(Exception ex, WebRequest request){

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .withDescription("Server Error").withDetails(Arrays.asList(ex.getMessage())).build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityFareConflictException.class)
    public final ResponseEntity<GenericResponseFareWebResponse> handleEntityFareConflictException(
            EntityFareConflictException ex, WebRequest request){

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.CONFLICT.value())
                        .withDescription("Record Exists into BBDD").withDetails(Arrays.asList(ex.getLocalizedMessage()))
                        .build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityFareNotFoundException.class)
    public final ResponseEntity<GenericResponseFareWebResponse> handleEntityFareNotFoundException(
            EntityFareNotFoundException ex, WebRequest request){

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.NO_CONTENT.value())
                        .withDescription("Record Not Exists into BBDD")
                        .withDetails(Arrays.asList(ex.getLocalizedMessage())).build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CustomUsernameNotFoundException.class)
    public final ResponseEntity<GenericResponseFareWebResponse> handleUsernameNotFoundException(
            CustomUsernameNotFoundException ex, WebRequest request){

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.NO_CONTENT.value())
                        .withDescription("Username Not Exists into BBDD")
                        .withDetails(Arrays.asList(ex.getLocalizedMessage())).build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(AuthorizationTokenException.class)
    public final ResponseEntity<GenericResponseFareWebResponse> handleAuthorizationTokenException(
            AuthorizationTokenException ex, WebRequest request){

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.FORBIDDEN.value()).withDescription("Token invalid")
                        .withDetails(Arrays.asList(ex.getLocalizedMessage())).build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<GenericResponseFareWebResponse> handleAccessDeniedException(AccessDeniedException ex,
            WebRequest request){

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.UNAUTHORIZED.value()).withDescription("Unauthorised")
                        .withDetails(Arrays.asList(ex.getLocalizedMessage())).build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request){

        List<String> details = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity(GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.BAD_REQUEST.value())
                        .withDescription("Validation Failed").withDetails(details).build())
                .withStatus(ERROR).withData(null).build(), HttpStatus.BAD_REQUEST);
    }

}
