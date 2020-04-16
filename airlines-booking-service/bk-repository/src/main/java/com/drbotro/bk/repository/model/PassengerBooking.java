package com.drbotro.bk.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerBooking extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long passengerId;

    private String firstName;
    private String lastName;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "booking_record_id")
    private BookingRecordBooking bookingRecord;

    private PassengerBooking(PassengerBuilder builder){
        this.passengerId = builder.passengerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.bookingRecord = builder.bookingRecord;
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

    public BookingRecordBooking getBookingRecord(){
        return bookingRecord;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof PassengerBooking)){
            return false;
        }
        final PassengerBooking castOther = (PassengerBooking) other;
        return new EqualsBuilder().append(passengerId, castOther.passengerId).append(firstName, castOther.firstName)
                .append(lastName, castOther.lastName).append(gender, castOther.gender)
                .append(bookingRecord, castOther.bookingRecord).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(passengerId).append(firstName).append(lastName).append(gender)
                .append(bookingRecord).toHashCode();
    }

    public static PassengerBuilder builder(){
        return new PassengerBuilder();
    }

    public PassengerBuilder cloneBuilder(){
        return new PassengerBuilder().withPassengerId(this.passengerId).withFirstname(this.firstName)
                .withLastName(this.lastName).withGender(this.gender).withBookingRecord(this.bookingRecord);
    }

    public interface IPassengerBuilder{
        PassengerBooking build();
    }

    public static final class PassengerBuilder implements IPassengerBuilder{

        private long passengerId;
        private String firstName;
        private String lastName;
        private String gender;
        private BookingRecordBooking bookingRecord;

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

        public PassengerBuilder withBookingRecord(BookingRecordBooking bookingRecord){
            this.bookingRecord = bookingRecord;
            return self();
        }

        @Override
        public PassengerBooking build(){
            return new PassengerBooking(this);
        }

        private PassengerBuilder self(){
            return this;
        }
    }

}
