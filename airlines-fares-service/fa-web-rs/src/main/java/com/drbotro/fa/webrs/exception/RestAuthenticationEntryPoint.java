package com.drbotro.fa.webrs.exception;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.drbotro.fa.common.exception.ApiError;
import com.drbotro.fa.webapi.response.GenericResponseFareWebResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

    private static final String ERROR = "KO";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException{

        final GenericResponseFareWebResponse genericResponseFareWebResponse = GenericResponseFareWebResponse.builder()
                .withError(ApiError.builder().withCode(HttpStatus.UNAUTHORIZED.value()).withDescription("Unauthorised")
                        .withDetails(Arrays.asList(authException.getLocalizedMessage())).build())
                .withStatus(ERROR).withData(null).build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), genericResponseFareWebResponse);

    }

}
