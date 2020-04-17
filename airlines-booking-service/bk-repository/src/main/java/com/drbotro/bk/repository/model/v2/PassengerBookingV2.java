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

@SuppressWarnings("serial")
@Entity
@Table(name = "passenger_booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerBookingV2 extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private long passengerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    private PassengerBookingV2(PassengerBuilder builder){
        this.passengerId = builder.passengerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
    }

    public Long getPassengerId(){
        return passengerId;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getGender(){
        return gender;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof PassengerBookingV2)){
            return false;
        }
        final PassengerBookingV2 castOther = (PassengerBookingV2) other;
        return new EqualsBuilder().append(passengerId, castOther.passengerId).append(firstName, castOther.firstName)
                .append(lastName, castOther.lastName).append(gender, castOther.gender).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(passengerId).append(firstName).append(lastName).append(gender).toHashCode();
    }

    public static PassengerBuilder builder(){
        return new PassengerBuilder();
    }

    public PassengerBuilder cloneBuilder(){
        return new PassengerBuilder().withPassengerId(this.passengerId).withFirstname(this.firstName)
                .withLastName(this.lastName).withGender(this.gender);
    }

    public interface IPassengerBuilder{
        PassengerBookingV2 build();
    }

    public static final class PassengerBuilder implements IPassengerBuilder{

        private long passengerId;
        private String firstName;
        private String lastName;
        private String gender;

        public PassengerBuilder withPassengerId(long passengerId){
            this.passengerId = passengerId;
            return self();
        }

        public PassengerBuilder withFirstname(String firstName){
            this.firstName = firstName;
            return self();
        }

        public PassengerBuilder withLastName(String lastName){
            this.lastName = lastName;
            return self();
        }

        public PassengerBuilder withGender(String gender){
            this.gender = gender;
            return self();
        }

        @Override
        public PassengerBookingV2 build(){
            return new PassengerBookingV2(this);
        }

        private PassengerBuilder self(){
            return this;
        }
    }

}
