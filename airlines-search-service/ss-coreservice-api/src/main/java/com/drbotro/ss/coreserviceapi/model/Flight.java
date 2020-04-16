package com.drbotro.ss.coreserviceapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.ss.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flight_search")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "flight_date")
    private String flightDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fares_seach")
    private Fares fares;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_inventory_search")
    private Inventory inventory;

    private Flight(FlightBuilder builder){
        this.flightNumber = builder.flightNumber;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
        this.fares = builder.fares;
        this.inventory = builder.inventory;
    }

    public String getFligthNumber(){
        return flightNumber;
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

    public Fares getFares(){
        return fares;
    }

    public Inventory getInventory(){
        return inventory;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Flight)){
            return false;
        }
        final Flight castOther = (Flight) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(origin, castOther.origin).append(destination, castOther.destination)
                .append(flightDate, castOther.flightDate).append(fares, castOther.fares)
                .append(inventory, castOther.inventory).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(origin).append(destination)
                .append(flightDate).append(fares).append(inventory).toHashCode();
    }

    public static FlightBuilder builder(){
        return new FlightBuilder();
    }

    public FlightBuilder cloneBuilder(){
        return new FlightBuilder().withFlightNumber(this.flightNumber).withOrigin(this.origin)
                .withDestination(this.destination).withFlightDate(this.flightDate).withFares(this.fares)
                .withInventory(this.inventory);
    }

    public interface IFlight{
        Flight build();
    }

    public static class FlightBuilder implements IFlight{

        private String flightNumber;
        private String origin;
        private String destination;
        private String flightDate;
        private Fares fares;
        private Inventory inventory;

        public FlightBuilder withFlightNumber(String fligthNumber){
            this.flightNumber = fligthNumber;
            return self();
        }

        public FlightBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public FlightBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public FlightBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public FlightBuilder withFares(Fares fares){
            this.fares = fares;
            return self();
        }

        public FlightBuilder withInventory(Inventory inventory){
            this.inventory = inventory;
            return self();
        }

        @Override
        public Flight build(){
            return new Flight(this);
        }

        private FlightBuilder self(){
            return this;
        }

    }

}
