package com.drbotro.fa.webrs.exception;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.drbotro.fa.common.exception.ApiError;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler{

    private static final String ERROR = "KO";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException{

        final GenericResponseFareWebResponse genericResponseFareWebResponse = GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.FORBIDDEN.value()).withDescription("Access Denied")
                        .withDetails(Arrays.asList(accessDeniedException.getLocalizedMessage())).build())
                .withStatus(ERROR).withData(null).build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), genericResponseFareWebResponse);
    }

}
