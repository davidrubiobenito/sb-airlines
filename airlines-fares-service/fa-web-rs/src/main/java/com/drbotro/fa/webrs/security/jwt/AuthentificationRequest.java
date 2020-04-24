package com.drbotro.fa.webrs.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationRequest{

    private String username;
    private String password;

}
