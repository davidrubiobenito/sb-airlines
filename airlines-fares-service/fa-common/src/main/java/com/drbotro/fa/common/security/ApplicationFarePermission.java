package com.drbotro.fa.common.security;

public enum ApplicationFarePermission{

    FARE_READ("fare:read"), FARE_WRITE("fare:write");

    private final String permission;

    ApplicationFarePermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }

}
