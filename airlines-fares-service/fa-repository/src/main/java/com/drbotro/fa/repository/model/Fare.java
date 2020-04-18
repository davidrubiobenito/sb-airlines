package com.drbotro.fa.repository.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.fa.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "fare")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fare extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fare")
    private Long idFare;
    @Column(name = "flight_number")
    private String flightNumber;
    @Column(name = "flight_date")
    private String flightDate;
    @Column(name = "fare")
    private BigDecimal fare;
    @Column(name = "currency")
    private String currency;

    public Fare(FareBuilder builder){
        this.idFare = builder.idFare;
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.fare = builder.fare;
        this.currency = builder.currency;
    }

    public Long getFareId(){
        return idFare;
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
        if(!(other instanceof Fare)){
            return false;
        }
        final Fare castOther = (Fare) other;
        return new EqualsBuilder().append(idFare, castOther.idFare).append(flightNumber, castOther.flightNumber)
                .append(flightDate, castOther.flightDate).append(fare, castOther.fare)
                .append(currency, castOther.currency).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(idFare).append(flightNumber).append(flightDate).append(fare)
                .append(currency).toHashCode();
    }

    public static FareBuilder builder(){
        return new FareBuilder();
    }

    public FareBuilder cloneBuilder(){
        return new FareBuilder().withFareId(this.idFare).withFlightNumber(this.flightNumber)
                .withFlightDate(this.flightDate).withFare(this.fare).withCurrency(this.currency);
    }

    public interface IFareBuilder{
        Fare build();
    }

    public static class FareBuilder implements IFareBuilder{

        private Long idFare;
        private String flightNumber;
        private String flightDate;
        private BigDecimal fare;
        private String currency;

        public FareBuilder withFareId(Long idFare){
            this.idFare = idFare;
            return self();
        }

        public FareBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public FareBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public FareBuilder withFare(BigDecimal fare){
            this.fare = fare;
            return self();
        }

        public FareBuilder withCurrency(String currency){
            this.currency = currency;
            return self();
        }

        @Override
        public Fare build(){
            return new Fare(this);
        }

        private FareBuilder self(){
            return this;
        }
    }

}
