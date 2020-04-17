package com.drbotro.bk.webapi.version;

public class Version{

    public static final String V1 = "/v1";
    public static final String V2 = "/v2";

    private Version(){
    }

    public static String getVersionV1(){
        return V1;
    }

    public static String getVersionV2(){
        return V2;
    }

}
