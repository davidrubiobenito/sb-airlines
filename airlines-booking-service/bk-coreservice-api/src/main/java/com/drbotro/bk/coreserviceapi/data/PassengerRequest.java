package com.drbotro.bk.coreserviceapi.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRequest extends AbstractModelBean{

    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private BookingRecordRequest bookingRecordRequest;

    private PassengerRequest(PassengerRequestBuilder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.bookingRecordRequest = builder.bookingRecordRequest;
    }

    public Long getId(){
        return id;
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

    public BookingRecordRequest getBookingRecordRequest(){
        return bookingRecordRequest;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof PassengerRequest)){
            return false;
        }
        final PassengerRequest castOther = (PassengerRequest) other;
        return new EqualsBuilder().append(id, castOther.id).append(firstName, castOther.firstName)
                .append(lastName, castOther.lastName).append(gender, castOther.gender)
                .append(bookingRecordRequest, castOther.bookingRecordRequest).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(firstName).append(lastName).append(gender)
                .append(bookingRecordRequest).toHashCode();
    }

    public static PassengerRequestBuilder builder(){
        return new PassengerRequestBuilder();
    }

    public PassengerRequestBuilder cloneBuilder(){
        return new PassengerRequestBuilder().withId(this.id).withFirstname(this.firstName).withLastName(this.lastName)
                .withGender(this.gender).withBookingRecordRequest(this.bookingRecordRequest);
    }

    public interface IPassengerRequestBuilder{
        PassengerRequest build();
    }

    public static final class PassengerRequestBuilder implements IPassengerRequestBuilder{

        private long id;
        private String firstName;
        private String lastName;
        private String gender;
        private BookingRecordRequest bookingRecordRequest;

        public PassengerRequestBuilder withId(long id){
            this.id = id;
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

        public PassengerRequestBuilder withBookingRecordRequest(BookingRecordRequest bookingRecordRequest){
            this.bookingRecordRequest = bookingRecordRequest;
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
