package com.drbotro.fa.webapi.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.fa.common.model.AbstractModelBean;

public class FareWebResponse extends AbstractModelBean{

    private long id;
    private String flightNumber;
    private String flightDate;
    private String fare;

    public FareWebResponse(FaresWebResponseBuilder builder){
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.fare = builder.fare;
    }

    public long getId(){
        return id;
    }

    public String getFlightNumber(){
        return flightNumber;
    }

    public String getFlightDate(){
        return flightDate;
    }

    public String getFare(){
        return fare;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof FareWebResponse)){
            return false;
        }
        final FareWebResponse castOther = (FareWebResponse) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(flightDate, castOther.flightDate).append(fare, castOther.fare).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(flightDate).append(fare).toHashCode();
    }

    public static FaresWebResponseBuilder builder(){
        return new FaresWebResponseBuilder();
    }

    public FaresWebResponseBuilder cloneBuilder(){
        return new FaresWebResponseBuilder().withId(this.id).withFlightNumber(this.flightNumber)
                .withFlightDate(this.flightDate).withFare(this.fare);
    }

    public interface IFaresWebResponseBuilder{
        FareWebResponse build();
    }

    public static class FaresWebResponseBuilder implements IFaresWebResponseBuilder{

        private long id;
        private String flightNumber;
        private String flightDate;
        private String fare;

        public FaresWebResponseBuilder withId(long id){
            this.id = id;
            return self();
        }

        public FaresWebResponseBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public FaresWebResponseBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public FaresWebResponseBuilder withFare(String fare){
            this.fare = fare;
            return self();
        }

        @Override
        public FareWebResponse build(){
            return new FareWebResponse(this);
        }

        private FaresWebResponseBuilder self(){
            return this;
        }
    }

}
