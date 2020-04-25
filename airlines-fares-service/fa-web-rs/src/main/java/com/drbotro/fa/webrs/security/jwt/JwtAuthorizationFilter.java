package com.drbotro.fa.webrs.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.drbotro.fa.common.exception.AuthorizationTokenException;
import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

public class JwtAuthorizationFilter extends OncePerRequestFilter{

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    /*
    private final AuthenticationManager authenticationManager;
    
    @Autowired
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        String authorizationHeader = request.getHeader(SecurityConstants.TOKEN_HEADER);

        if(Strings.isNullOrEmpty(authorizationHeader)
                || !authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

        try{

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();

            String username = body.getSubject();

            List<Map<String, String>> authorities = (List<Map<String, String>>) body
                    .get(SecurityConstants.TOKEN_AUTHORITIES);

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get(SecurityConstants.TOKEN_AUTHORITY)))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                    simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch(RuntimeException exception){
            throw new AuthorizationTokenException(exception.getMessage());
        }

        filterChain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);

        if(StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)){
            try{

                byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

                Jws<Claims> parsedToken = Jwts.parser().setSigningKey(signingKey)
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));

                String username = parsedToken.getBody().getSubject();

                List<Map<String, String>> authorities = (List<Map<String, String>>) parsedToken.getBody()
                        .get(SecurityConstants.TOKEN_AUTHORITIES);

                Set<SimpleGrantedAuthority> simpleGrantedAuthorities = (Set<SimpleGrantedAuthority>) authorities
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.get(SecurityConstants.TOKEN_AUTHORITY)))
                        .collect(Collectors.toList());

                if(StringUtils.isNotEmpty(username)){
                    return new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
                }

            }catch(ExpiredJwtException exception){
                throw new IllegalArgumentException(
                        String.format("Request to parse expired JWT failed : %s", exception.getMessage()));
            }catch(UnsupportedJwtException exception){
                throw new IllegalArgumentException(
                        String.format("Request to parse unsupported JWT failed : %s", exception.getMessage()));
            }catch(MalformedJwtException exception){
                throw new IllegalArgumentException(
                        String.format("Request to parse invalid JWT failed : %s", exception.getMessage()));
            }catch(SignatureException exception){
                throw new IllegalArgumentException(
                        String.format("Request to parse JWT with invalid signature : %s", exception.getMessage()));
            }catch(IllegalArgumentException exception){
                throw new IllegalArgumentException(
                        String.format("Request to parse empty or null JWT : %s", exception.getMessage()));
            }
        }

        return null;
    }

}
