package com.drbotro.ss.coreserviceapi.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.ss.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchQueryRequest extends AbstractModelBean{

    private String origin;
    private String destination;
    private String flightDate;

    private SearchQueryRequest(SearchQueryBuilder builder){
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
        if(!(other instanceof SearchQueryRequest)){
            return false;
        }
        final SearchQueryRequest castOther = (SearchQueryRequest) other;
        return new EqualsBuilder().append(origin, castOther.origin).append(destination, castOther.destination)
                .append(flightDate, castOther.flightDate).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(origin).append(destination).append(flightDate).toHashCode();
    }

    public static SearchQueryBuilder builder(){
        return new SearchQueryBuilder();
    }

    public SearchQueryBuilder cloneBuilder(){
        return new SearchQueryBuilder().withOrigin(this.origin).withDestination(this.destination)
                .withFlightDate(this.flightDate);
    }

    public interface ISearchQuery{
        SearchQueryRequest build();
    }

    public static class SearchQueryBuilder implements ISearchQuery{

        private String origin;
        private String destination;
        private String flightDate;

        public SearchQueryBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public SearchQueryBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public SearchQueryBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        @Override
        public SearchQueryRequest build(){
            return new SearchQueryRequest(this);
        }

        private SearchQueryBuilder self(){
            return this;
        }

    }

}
