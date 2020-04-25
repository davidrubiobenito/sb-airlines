package com.drbotro.fa.webrs.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.drbotro.fa.webapi.response.GenericResponseTokenWebResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;

    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException{

        try{
            AuthentificationRequest authentificationRequest = new ObjectMapper().readValue(request.getInputStream(),
                    AuthentificationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authentificationRequest.getUsername(), authentificationRequest.getPassword());

            return authenticationManager.authenticate(authentication);
        }catch(IOException ioe){
            throw new RuntimeException(ioe);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain, Authentication authentication) throws IOException, ServletException{

        // @formatter:off
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(SecurityConstants.TOKEN_AUTHORITIES, authentication.getAuthorities())
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setIssuedAt(new Date())
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes()), SignatureAlgorithm.HS512).compact();
 
        // @formatter:on

        final GenericResponseTokenWebResponse genericResponseTokenWebResponse = GenericResponseTokenWebResponse
                .builder().withStatus("OK").withError(null)
                .withData(Arrays.asList(SecurityConstants.TOKEN_PREFIX + token)).build();

        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), genericResponseTokenWebResponse);

    }

}
