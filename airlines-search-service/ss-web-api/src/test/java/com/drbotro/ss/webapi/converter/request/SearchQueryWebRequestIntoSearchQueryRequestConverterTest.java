package com.drbotro.ss.webapi.converter.request;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.drbotro.ss.coreserviceapi.data.SearchQueryRequest;
import com.drbotro.ss.webapi.request.SearchQueryWebRequest;

public class SearchQueryWebRequestIntoSearchQueryRequestConverterTest{

    private static final String ORIGIN = "SEA";
    private static final String DESTINATION = "SFO";
    private static final String FLIGHT_DATE = "22-JAN-16";

    private static final SearchQueryWebRequest SEARCH_QUERY_WEB_REQUEST = SearchQueryWebRequest.builder()
            .withOrigin(ORIGIN).withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).build();
    private static final SearchQueryRequest SEARCH_QUERY_REQUEST = SearchQueryRequest.builder().withOrigin(ORIGIN)
            .withDestination(DESTINATION).withFlightDate(FLIGHT_DATE).build();

    private final SearchQueryWebRequestIntoSearchQueryRequestConverter converter = new SearchQueryWebRequestIntoSearchQueryRequestConverter();

    @Test
    public void shouldConvertNull(){
        assertThat(converter.convert(null), is(nullValue()));
    }

    @Test
    public void whenSearchQueryWebRequest_shouldConvert_SearchQueryRequest(){
        assertThat(converter.convert(SEARCH_QUERY_WEB_REQUEST), is(SEARCH_QUERY_REQUEST));
    }

}
