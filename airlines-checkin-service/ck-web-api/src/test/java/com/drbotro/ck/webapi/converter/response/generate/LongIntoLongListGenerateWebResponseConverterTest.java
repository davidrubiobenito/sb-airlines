package com.drbotro.ck.webapi.converter.response.generate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.drbotro.ck.common.response.GenericResponse;

public class LongIntoLongListGenerateWebResponseConverterTest{

    private static final String STATUS_OK = "OK";
    private static final Long ID_CHECKIN_RECORD = 1L;

    public static final GenericResponse<Long> GENERIC_LONG_WEB_RESPONSE = GenericResponse.builder()
            .withStatus(STATUS_OK).withData(Arrays.asList(ID_CHECKIN_RECORD)).withError(null).build();

    private LongIntoLongListGenerateWebResponseConverter converter = new LongIntoLongListGenerateWebResponseConverter();

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenFlightWebResponse_shouldConvert_FlightGenerateWebResponse(){
        assertThat(converter.convert(ID_CHECKIN_RECORD), is(GENERIC_LONG_WEB_RESPONSE));
    }
}
