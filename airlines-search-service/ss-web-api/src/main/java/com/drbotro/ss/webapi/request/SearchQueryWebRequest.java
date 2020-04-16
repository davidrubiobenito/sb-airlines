package com.drbotro.ss.webapi.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.ss.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchQueryWebRequest extends AbstractModelBean{

    private String origin;
    private String destination;
    private String flightDate;

    private SearchQueryWebRequest(SearchQueryWebRequestBuilder builder){
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
    }

    public String getOrigin(){
        return origin;
    }

    public String getDestination(){
        return destination;
    }

    public String getFlightDate(){
        return flightDate;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof SearchQueryWebRequest)){
            return false;
        }
        final SearchQueryWebRequest castOther = (SearchQueryWebRequest) other;
        return new EqualsBuilder().append(origin, castOther.origin).append(destination, castOther.destination)
                .append(flightDate, castOther.flightDate).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(origin).append(destination).append(flightDate).toHashCode();
    }

    public static SearchQueryWebRequestBuilder builder(){
        return new SearchQueryWebRequestBuilder();
    }

    public SearchQueryWebRequestBuilder cloneBuilder(){
        return new SearchQueryWebRequestBuilder().withOrigin(this.origin).withDestination(this.destination)
                .withFlightDate(this.flightDate);
    }

    public interface ISearchQueryWebRequest{
        SearchQueryWebRequest build();
    }

    public static class SearchQueryWebRequestBuilder implements ISearchQueryWebRequest{

        private String origin;
        private String destination;
        private String flightDate;

        public SearchQueryWebRequestBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public SearchQueryWebRequestBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public SearchQueryWebRequestBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        @Override
        public SearchQueryWebRequest build(){
            return new SearchQueryWebRequest(this);
        }

        private SearchQueryWebRequestBuilder self(){
            return this;
        }

    }

}
