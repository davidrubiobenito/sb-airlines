package com.drbotro.bk.webapi.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerWebRequest extends AbstractModelBean{

    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private BookingRecordWebRequest bookingRecordWebRequest;

    private PassengerWebRequest(PassengerWebRequestBuilder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.bookingRecordWebRequest = builder.bookingRecordWebRequest;
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

    public BookingRecordWebRequest getBookingRecordWebRequest(){
        return bookingRecordWebRequest;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof PassengerWebRequest)){
            return false;
        }
        final PassengerWebRequest castOther = (PassengerWebRequest) other;
        return new EqualsBuilder().append(id, castOther.id).append(firstName, castOther.firstName)
                .append(lastName, castOther.lastName).append(gender, castOther.gender)
                .append(bookingRecordWebRequest, castOther.bookingRecordWebRequest).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(firstName).append(lastName).append(gender)
                .append(bookingRecordWebRequest).toHashCode();
    }

    public static PassengerWebRequestBuilder builder(){
        return new PassengerWebRequestBuilder();
    }

    public PassengerWebRequestBuilder cloneBuilder(){
        return new PassengerWebRequestBuilder().withId(this.id).withFirstname(this.firstName)
                .withLastName(this.lastName).withGender(this.gender)
                .withBookingRecordWebRequest(this.bookingRecordWebRequest);
    }

    public interface IPassengerWebRequestBuilder{
        PassengerWebRequest build();
    }

    public static final class PassengerWebRequestBuilder implements IPassengerWebRequestBuilder{

        private long id;
        private String firstName;
        private String lastName;
        private String gender;
        private BookingRecordWebRequest bookingRecordWebRequest;

        public PassengerWebRequestBuilder withId(long id){
            this.id = id;
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

        public PassengerWebRequestBuilder withBookingRecordWebRequest(BookingRecordWebRequest bookingRecordWebRequest){
            this.bookingRecordWebRequest = bookingRecordWebRequest;
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
