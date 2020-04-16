package com.drbotro.ss.coreserviceapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.ss.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fares_search")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fares extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "fare")
    private String fare;
    @Column(name = "currency")
    private String currency;

    private Fares(FaresBuilder builder){
        this.fare = builder.fare;
        this.currency = builder.currency;
    }

    public String getFare(){
        return fare;
    }

    public String getCurrency(){
        return currency;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Fares)){
            return false;
        }
        final Fares castOther = (Fares) other;
        return new EqualsBuilder().append(id, castOther.id).append(fare, castOther.fare)
                .append(currency, castOther.currency).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(fare).append(currency).toHashCode();
    }

    public static FaresBuilder builder(){
        return new FaresBuilder();
    }

    public FaresBuilder cloneBuilder(){
        return new FaresBuilder().withFare(this.fare).withCurrency(this.currency);
    }

    public interface IFares{
        Fares build();
    }

    public static class FaresBuilder implements IFares{

        private String fare;
        private String currency;

        public FaresBuilder withFare(String fare){
            this.fare = fare;
            return self();
        }

        public FaresBuilder withCurrency(String currency){
            this.currency = currency;
            return self();
        }

        @Override
        public Fares build(){
            return new Fares(this);
        }

        private FaresBuilder self(){
            return this;
        }

    }

}
