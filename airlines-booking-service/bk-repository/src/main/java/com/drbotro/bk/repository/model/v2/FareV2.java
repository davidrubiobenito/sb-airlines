package com.drbotro.bk.repository.model.v2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fare_fare")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FareV2 extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "flight_number")
    private String flightNumber;
    @Column(name = "flight_date")
    private String flightDate;
    @Column(name = "fare")
    private String fare;

    public FareV2(FareBuilder builder){
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
        if(!(other instanceof FareV2)){
            return false;
        }
        final FareV2 castOther = (FareV2) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(flightDate, castOther.flightDate).append(fare, castOther.fare).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(flightDate).append(fare).toHashCode();
    }

    public static FareBuilder builder(){
        return new FareBuilder();
    }

    public FareBuilder cloneBuilder(){
        return new FareBuilder().withId(this.id).withFlightNumber(this.flightNumber).withFlightDate(this.flightDate)
                .withFare(this.fare);
    }

    public interface IFareBuilder{
        FareV2 build();
    }

    public static class FareBuilder implements IFareBuilder{

        private long id;
        private String flightNumber;
        private String flightDate;
        private String fare;

        public FareBuilder withId(long id){
            this.id = id;
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

        public FareBuilder withFare(String fare){
            this.fare = fare;
            return self();
        }

        @Override
        public FareV2 build(){
            return new FareV2(this);
        }

        private FareBuilder self(){
            return this;
        }
    }

}
