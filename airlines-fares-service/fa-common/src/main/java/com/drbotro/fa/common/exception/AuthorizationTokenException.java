package com.drbotro.fa.common.exception;

@SuppressWarnings("serial")
public class AuthorizationTokenException extends RuntimeException{

    public AuthorizationTokenException(String exception){
        super(exception);
    }

}
