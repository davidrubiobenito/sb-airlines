package com.drbotro.fa.coreserviceapi.data.request;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.fa.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FareRequest extends AbstractModelBean{

    private String flightNumber;
    private String flightDate;
    private BigDecimal fare;
    private String currency;

    public FareRequest(FareRequestBuilder builder){
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.fare = builder.fare;
        this.currency = builder.currency;
    }

    public String getFlightNumber(){
        return flightNumber;
    }

    public String getFlightDate(){
        return flightDate;
    }

    public BigDecimal getFare(){
        return fare;
    }

    public String getCurrency(){
        return currency;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof FareRequest)){
            return false;
        }
        final FareRequest castOther = (FareRequest) other;
        return new EqualsBuilder().append(flightNumber, castOther.flightNumber).append(flightDate, castOther.flightDate)
                .append(fare, castOther.fare).append(currency, castOther.currency).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(flightNumber).append(flightDate).append(fare).append(currency).toHashCode();
    }

    public static FareRequestBuilder builder(){
        return new FareRequestBuilder();
    }

    public FareRequestBuilder cloneBuilder(){
        return new FareRequestBuilder().withFlightNumber(this.flightNumber).withFlightDate(this.flightDate)
                .withFare(this.fare).withCurrency(this.currency);
    }

    public interface IFareRequestBuilder{
        FareRequest build();
    }

    public static class FareRequestBuilder implements IFareRequestBuilder{

        private String flightNumber;
        private String flightDate;
        private BigDecimal fare;
        private String currency;

        public FareRequestBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public FareRequestBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public FareRequestBuilder withFare(BigDecimal fare){
            this.fare = fare;
            return self();
        }

        public FareRequestBuilder withCurrency(String currency){
            this.currency = currency;
            return self();
        }

        @Override
        public FareRequest build(){
            return new FareRequest(this);
        }

        private FareRequestBuilder self(){
            return this;
        }
    }

}
