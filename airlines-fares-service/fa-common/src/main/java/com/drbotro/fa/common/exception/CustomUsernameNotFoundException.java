package com.drbotro.fa.common.exception;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class CustomUsernameNotFoundException extends AuthenticationException{

    public CustomUsernameNotFoundException(String exception){
        super(exception);
    }

}
