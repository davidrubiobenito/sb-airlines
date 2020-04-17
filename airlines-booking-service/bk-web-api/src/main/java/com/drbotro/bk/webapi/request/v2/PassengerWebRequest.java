package com.drbotro.bk.webapi.request.v2;

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
public class PassengerWebRequest extends AbstractModelBean{

    private long passengerWebRequestId;
    private String firstName;
    private String lastName;
    private String gender;

    private PassengerWebRequest(PassengerWebRequestBuilder builder){
        this.passengerWebRequestId = builder.passengerWebRequestId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
    }

    public Long getPassengerWebRequestId(){
        return passengerWebRequestId;
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
        if(!(other instanceof PassengerWebRequest)){
            return false;
        }
        final PassengerWebRequest castOther = (PassengerWebRequest) other;
        return new EqualsBuilder().append(passengerWebRequestId, castOther.passengerWebRequestId)
                .append(firstName, castOther.firstName).append(lastName, castOther.lastName)
                .append(gender, castOther.gender).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(passengerWebRequestId).append(firstName).append(lastName).append(gender)
                .toHashCode();
    }

    public static PassengerWebRequestBuilder builder(){
        return new PassengerWebRequestBuilder();
    }

    public PassengerWebRequestBuilder cloneBuilder(){
        return new PassengerWebRequestBuilder().withPassengerWebRequestId(getPassengerWebRequestId())
                .withFirstname(getFirstName()).withLastName(getLastName()).withGender(getGender());
    }

    public interface IPassengerWebRequestBuilder{
        PassengerWebRequest build();
    }

    public static final class PassengerWebRequestBuilder implements IPassengerWebRequestBuilder{

        private long passengerWebRequestId;
        private String firstName;
        private String lastName;
        private String gender;

        public PassengerWebRequestBuilder withPassengerWebRequestId(long passengerWebRequestId){
            this.passengerWebRequestId = passengerWebRequestId;
            return self();
        }

        public PassengerWebRequestBuilder withFirstname(String firstName){
            this.firstName = firstName;
            return self();
        }

        public PassengerWebRequestBuilder withLastName(String lastName){
            this.lastName = lastName;
            return self();
        }

        public PassengerWebRequestBuilder withGender(String gender){
            this.gender = gender;
            return self();
        }

        @Override
        public PassengerWebRequest build(){
            return new PassengerWebRequest(this);
        }

        private PassengerWebRequestBuilder self(){
            return this;
        }
    }

}
