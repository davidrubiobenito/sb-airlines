package com.drbotro.bk.coreserviceapi.data.request.v2;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRequest extends AbstractModelBean{

    private long passengerRequestId;
    private String firstName;
    private String lastName;
    private String gender;

    private PassengerRequest(PassengerRequestBuilder builder){
        this.passengerRequestId = builder.passengerRequestId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
    }

    public Long getPassengerRequestId(){
        return passengerRequestId;
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
        if(!(other instanceof PassengerRequest)){
            return false;
        }
        final PassengerRequest castOther = (PassengerRequest) other;
        return new EqualsBuilder().append(passengerRequestId, castOther.passengerRequestId)
                .append(firstName, castOther.firstName).append(lastName, castOther.lastName)
                .append(gender, castOther.gender).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(passengerRequestId).append(firstName).append(lastName).append(gender)
                .toHashCode();
    }

    public static PassengerRequestBuilder builder(){
        return new PassengerRequestBuilder();
    }

    public PassengerRequestBuilder cloneBuilder(){
        return new PassengerRequestBuilder().withPassengerRequestId(this.passengerRequestId)
                .withFirstname(this.firstName).withLastName(this.lastName).withGender(this.gender);
    }

    public interface IPassengerRequestBuilder{
        PassengerRequest build();
    }

    public static final class PassengerRequestBuilder implements IPassengerRequestBuilder{

        private long passengerRequestId;
        private String firstName;
        private String lastName;
        private String gender;

        public PassengerRequestBuilder withPassengerRequestId(long passengerRequestId){
            this.passengerRequestId = passengerRequestId;
            return self();
        }

        public PassengerRequestBuilder withFirstname(String firstName){
            this.firstName = firstName;
            return self();
        }

        public PassengerRequestBuilder withLastName(String lastName){
            this.lastName = lastName;
            return self();
        }

        public PassengerRequestBuilder withGender(String gender){
            this.gender = gender;
            return self();
        }

        @Override
        public PassengerRequest build(){
            return new PassengerRequest(this);
        }

        private PassengerRequestBuilder self(){
            return this;
        }
    }

}
