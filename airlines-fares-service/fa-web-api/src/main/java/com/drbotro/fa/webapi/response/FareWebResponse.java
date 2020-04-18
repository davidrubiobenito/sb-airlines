package com.drbotro.fa.webapi.response;

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
public class FareWebResponse extends AbstractModelBean{

    private Long id;
    private String flightNumber;
    private String flightDate;
    private BigDecimal fare;
    private String currency;

    public FareWebResponse(FaresWebResponseBuilder builder){
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.fare = builder.fare;
        this.currency = builder.currency;
    }

    public Long getId(){
        return id;
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
        if(!(other instanceof FareWebResponse)){
            return false;
        }
        final FareWebResponse castOther = (FareWebResponse) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(flightDate, castOther.flightDate).append(fare, castOther.fare)
                .append(currency, castOther.currency).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(flightDate).append(fare).append(currency)
                .toHashCode();
    }

    public static FaresWebResponseBuilder builder(){
        return new FaresWebResponseBuilder();
    }

    public FaresWebResponseBuilder cloneBuilder(){
        return new FaresWebResponseBuilder().withId(this.id).withFlightNumber(this.flightNumber)
                .withFlightNumber(this.flightDate).withFare(this.fare).withCurrency(this.currency);
    }

    public interface IFaresWebResponseBuilder{
        FareWebResponse build();
    }

    public static class FaresWebResponseBuilder implements IFaresWebResponseBuilder{

        private Long id;
        private String flightNumber;
        private String flightDate;
        private BigDecimal fare;
        private String currency;

        public FaresWebResponseBuilder withId(Long id){
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

        public FaresWebResponseBuilder withFare(BigDecimal fare){
            this.fare = fare;
            return self();
        }

        public FaresWebResponseBuilder withCurrency(String currency){
            this.currency = currency;
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
