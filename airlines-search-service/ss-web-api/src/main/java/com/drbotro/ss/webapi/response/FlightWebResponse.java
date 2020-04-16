package com.drbotro.ss.webapi.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.ss.common.model.AbstractModelBean;
import com.drbotro.ss.coreserviceapi.model.Fares;
import com.drbotro.ss.coreserviceapi.model.Inventory;

public class FlightWebResponse extends AbstractModelBean{

    private long id;
    private String fligthNumber;
    private String origin;
    private String destination;
    private String flightDate;
    private Fares fares;
    private Inventory inventory;

    private FlightWebResponse(FlightWebResponseBuilder builder){
        this.fligthNumber = builder.fligthNumber;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
        this.fares = builder.fares;
        this.inventory = builder.inventory;
    }

    public String getFligthNumber(){
        return fligthNumber;
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
        if(!(other instanceof FlightWebResponse)){
            return false;
        }
        final FlightWebResponse castOther = (FlightWebResponse) other;
        return new EqualsBuilder().append(id, castOther.id).append(fligthNumber, castOther.fligthNumber)
                .append(origin, castOther.origin).append(destination, castOther.destination)
                .append(flightDate, castOther.flightDate).append(fares, castOther.fares)
                .append(inventory, castOther.inventory).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(fligthNumber).append(origin).append(destination)
                .append(flightDate).append(fares).append(inventory).toHashCode();
    }

    public static FlightWebResponseBuilder builder(){
        return new FlightWebResponseBuilder();
    }

    public FlightWebResponseBuilder cloneBuilder(){
        return new FlightWebResponseBuilder().withFligthNumber(this.fligthNumber).withOrigin(this.origin)
                .withDestination(this.destination).withFlightDate(this.flightDate).withFares(this.fares)
                .withInventory(this.inventory);
    }

    public interface IFlightWebResponse{
        FlightWebResponse build();
    }

    public static class FlightWebResponseBuilder implements IFlightWebResponse{

        private String fligthNumber;
        private String origin;
        private String destination;
        private String flightDate;
        private Fares fares;
        private Inventory inventory;

        public FlightWebResponseBuilder withFligthNumber(String fligthNumber){
            this.fligthNumber = fligthNumber;
            return self();
        }

        public FlightWebResponseBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public FlightWebResponseBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public FlightWebResponseBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public FlightWebResponseBuilder withFares(Fares fares){
            this.fares = fares;
            return self();
        }

        public FlightWebResponseBuilder withInventory(Inventory inventory){
            this.inventory = inventory;
            return self();
        }

        @Override
        public FlightWebResponse build(){
            return new FlightWebResponse(this);
        }

        private FlightWebResponseBuilder self(){
            return this;
        }

    }

}
