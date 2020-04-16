package com.drbotro.ss.webapi.converter.request;

import org.springframework.stereotype.Component;

import com.drbotro.ss.common.converter.Converter;
import com.drbotro.ss.coreserviceapi.data.SearchQueryRequest;
import com.drbotro.ss.webapi.request.SearchQueryWebRequest;

@Component
public class SearchQueryWebRequestIntoSearchQueryRequestConverter
        implements Converter<SearchQueryWebRequest, SearchQueryRequest>{

    @Override
    public SearchQueryRequest convert(final SearchQueryWebRequest searchQueryWebRequest){
        if(searchQueryWebRequest == null){
            return null;
        }
        return SearchQueryRequest.builder().withOrigin(searchQueryWebRequest.getOrigin())
                .withDestination(searchQueryWebRequest.getDestination())
                .withFlightDate(searchQueryWebRequest.getFlightDate()).build();
    }
}
