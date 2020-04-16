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
@Table(name = "inventory_search")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "count")
    private int count;

    private Inventory(InventoryBuilder builder){
        this.count = builder.count;
    }

    public int getCount(){
        return count;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Inventory)){
            return false;
        }
        final Inventory castOther = (Inventory) other;
        return new EqualsBuilder().append(id, castOther.id).append(count, castOther.count).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(count).toHashCode();
    }

    public static InventoryBuilder builder(){
        return new InventoryBuilder();
    }

    public InventoryBuilder cloneBuilder(){
        return new InventoryBuilder().withCount(this.count);
    }

    public interface IInventory{
        Inventory build();
    }

    public static class InventoryBuilder implements IInventory{

        private int count;

        public InventoryBuilder withCount(int count){
            this.count = count;
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
