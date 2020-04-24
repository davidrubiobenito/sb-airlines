package com.drbotro.fa.webapi.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class FareWebRequest extends AbstractModelBean{

    @NotNull(message = "Flight Number cannot be missing or empty")
    @Size(min = 5, max = 5, message = "Flight Number have 5 characters")
    private String flightNumber;
    @NotNull(message = "Flight Date cannot be missing or empty")
    @Size(min = 9, max = 9, message = "Flight Date have 9 characters")
    private String flightDate;
    @NotNull(message = "Fare Number cannot be missing or empty")
    @DecimalMin("0.00")
    private BigDecimal fare;
    @NotNull(message = "Currency")
    @Size(min = 3, max = 3, message = "Currency have 3 characters")
    private String currency;

    public FareWebRequest(FareWebRequestBuilder builder){
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
        if(!(other instanceof FareWebRequest)){
            return false;
        }
        final FareWebRequest castOther = (FareWebRequest) other;
        return new EqualsBuilder().append(flightNumber, castOther.flightNumber).append(flightDate, castOther.flightDate)
                .append(fare, castOther.fare).append(currency, castOther.currency).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(flightNumber).append(flightDate).append(fare).append(currency).toHashCode();
    }

    public static FareWebRequestBuilder builder(){
        return new FareWebRequestBuilder();
    }

    public FareWebRequestBuilder cloneBuilder(){
        return new FareWebRequestBuilder().withFlightNumber(this.flightNumber).withFlightDate(this.flightDate)
                .withFare(this.fare).withCurrency(this.currency);
    }

    public interface IFareWebRequestBuilder{
        FareWebRequest build();
    }

    public static class FareWebRequestBuilder implements IFareWebRequestBuilder{

        private String flightNumber;
        private String flightDate;
        private BigDecimal fare;
        private String currency;

        public FareWebRequestBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public FareWebRequestBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public FareWebRequestBuilder withFare(BigDecimal fare){
            this.fare = fare;
            return self();
        }

        public FareWebRequestBuilder withCurrency(String currency){
            this.currency = currency;
            return self();
        }

        @Override
        public FareWebRequest build(){
            return new FareWebRequest(this);
        }

        private FareWebRequestBuilder self(){
            return this;
        }
    }

}
