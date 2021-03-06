package com.drbotro.bk.webapi.version;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class VersionTest{

    public static final String V1 = "/v1";
    public static final String V2 = "/v2";

    @Test
    public void shuoldReturnV1(){
        assertThat(Version.V1, is(V1));
    }

    @Test
    public void shuoldReturnV2(){
        assertThat(Version.V2, is(V2));
    }

}
