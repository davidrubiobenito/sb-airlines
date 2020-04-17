package com.drbotro.bk.coreserviceapi.data.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerResponse extends AbstractModelBean{

    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private BookingRecordResponse bookingRecordResponse;

    private PassengerResponse(PassengerResponseBuilder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.bookingRecordResponse = builder.bookingRecordResponse;
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

    public BookingRecordResponse getBookingRecordResponse(){
        return bookingRecordResponse;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof PassengerResponse)){
            return false;
        }
        final PassengerResponse castOther = (PassengerResponse) other;
        return new EqualsBuilder().append(id, castOther.id).append(firstName, castOther.firstName)
                .append(lastName, castOther.lastName).append(gender, castOther.gender)
                .append(bookingRecordResponse, castOther.bookingRecordResponse).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(firstName).append(lastName).append(gender)
                .append(bookingRecordResponse).toHashCode();
    }

    public static PassengerResponseBuilder builder(){
        return new PassengerResponseBuilder();
    }

    public PassengerResponseBuilder cloneBuilder(){
        return new PassengerResponseBuilder().withId(this.id).withFirstname(this.firstName).withLastName(this.lastName)
                .withGender(this.gender).withBookingRecordResponse(this.bookingRecordResponse);
    }

    public interface IPassengerResponseBuilder{
        PassengerResponse build();
    }

    public static final class PassengerResponseBuilder implements IPassengerResponseBuilder{

        private long id;
        private String firstName;
        private String lastName;
        private String gender;
        private BookingRecordResponse bookingRecordResponse;

        public PassengerResponseBuilder withId(long id){
            this.id = id;
            return self();
        }

        public PassengerResponseBuilder withFirstname(String firstName){
            this.firstName = firstName;
            return self();
        }

        public PassengerResponseBuilder withLastName(String lastName){
            this.lastName = lastName;
            return self();
        }

        public PassengerResponseBuilder withGender(String gender){
            this.gender = gender;
            return self();
        }

        public PassengerResponseBuilder withBookingRecordResponse(BookingRecordResponse bookingRecordResponse){
            this.bookingRecordResponse = bookingRecordResponse;
            return self();
        }

        @Override
        public PassengerResponse build(){
            return new PassengerResponse(this);
        }

        private PassengerResponseBuilder self(){
            return this;
        }
    }

}
