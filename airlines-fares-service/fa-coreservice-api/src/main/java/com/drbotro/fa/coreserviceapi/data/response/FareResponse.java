package com.drbotro.fa.coreserviceapi.data.response;

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
public class FareResponse extends AbstractModelBean{

    private Long fareResponseId;
    private String flightNumber;
    private String flightDate;
    private BigDecimal fare;
    private String currency;

    public FareResponse(FareResponseBuilder builder){
        this.fareResponseId = builder.fareResponseId;
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.fare = builder.fare;
        this.currency = builder.currency;
    }

    public Long getFareResponseId(){
        return fareResponseId;
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
        if(!(other instanceof FareResponse)){
            return false;
        }
        final FareResponse castOther = (FareResponse) other;
        return new EqualsBuilder().append(fareResponseId, castOther.fareResponseId)
                .append(flightNumber, castOther.flightNumber).append(flightDate, castOther.flightDate)
                .append(fare, castOther.fare).append(currency, castOther.currency).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(fareResponseId).append(flightNumber).append(flightDate).append(fare)
                .append(currency).toHashCode();
    }

    public static FareResponseBuilder builder(){
        return new FareResponseBuilder();
    }

    public FareResponseBuilder cloneBuilder(){
        return new FareResponseBuilder().withFareResponseId(this.fareResponseId).withFlightNumber(this.flightNumber)
                .withFlightDate(this.flightDate).withFare(this.fare).withCurrency(this.currency);
    }

    public interface IFareResponseBuilder{
        FareResponse build();
    }

    public static class FareResponseBuilder implements IFareResponseBuilder{

        private Long fareResponseId;
        private String flightNumber;
        private String flightDate;
        private BigDecimal fare;
        private String currency;

        public FareResponseBuilder withFareResponseId(Long fareResponseId){
            this.fareResponseId = fareResponseId;
            return self();
        }

        public FareResponseBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public FareResponseBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public FareResponseBuilder withFare(BigDecimal fare){
            this.fare = fare;
            return self();
        }

        public FareResponseBuilder withCurrency(String currency){
            this.currency = currency;
            return self();
        }

        @Override
        public FareResponse build(){
            return new FareResponse(this);
        }

        private FareResponseBuilder self(){
            return this;
        }
    }

}
