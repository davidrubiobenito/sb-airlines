package com.drbotro.bk.repository.model;

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
@Table(name = "inventory_booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "flight_number")
    private String flightNumber;
    @Column(name = "flight_date")
    private String flightDate;
    @Column(name = "available")
    private int available;

    private Inventory(InventoryBuilder builder){
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.available = builder.available;
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

    public int getAvailable(){
        return available;
    }

    public boolean isAvailable(int count){
        return((getAvailable() - count) > 5);
    }

    public int getBookableInventory(){
        return getAvailable() - 5;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Inventory)){
            return false;
        }
        final Inventory castOther = (Inventory) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(flightDate, castOther.flightDate).append(available, castOther.available).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(flightDate).append(available).toHashCode();
    }

    public static InventoryBuilder builder(){
        return new InventoryBuilder();
    }

    public InventoryBuilder cloneBuilder(){
        return new InventoryBuilder().withFlightNumber(this.flightNumber).withFlightDate(this.flightDate)
                .withAvailable(this.available);
    }

    public interface IInventoryBuilder{
        Inventory build();
    }

    public static final class InventoryBuilder implements IInventoryBuilder{

        private long id;
        private String flightNumber;
        private String flightDate;
        private int available;

        public InventoryBuilder withId(long id){
            this.id = id;
            return self();
        }

        public InventoryBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public InventoryBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public InventoryBuilder withAvailable(int available){
            this.available = available;
            return self();
        }

        @Override
        public Inventory build(){
            return new Inventory(this);
        }

        private InventoryBuilder self(){
            return this;
        }
    }

}
