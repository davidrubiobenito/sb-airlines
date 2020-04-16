package com.drbotro.bk.repository.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passenger_booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passenger_id")
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_record_id")
    @JsonIgnore
    private BookingRecord bookingRecord;

    private Passenger(PassengerBuilder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.bookingRecord = builder.bookingRecord;
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

    public BookingRecord getBookingRecord(){
        return bookingRecord;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Passenger)){
            return false;
        }
        final Passenger castOther = (Passenger) other;
        return new EqualsBuilder().append(id, castOther.id).append(firstName, castOther.firstName)
                .append(lastName, castOther.lastName).append(gender, castOther.gender)
                .append(bookingRecord, castOther.bookingRecord).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(firstName).append(lastName).append(gender).append(bookingRecord)
                .toHashCode();
    }

    public static PassengerBuilder builder(){
        return new PassengerBuilder();
    }

    public PassengerBuilder cloneBuilder(){
        return new PassengerBuilder().withId(this.id).withFirstname(this.firstName).withLastName(this.lastName)
                .withGender(this.gender).withBookingRecord(this.bookingRecord);
    }

    public interface IPassengerBuilder{
        Passenger build();
    }

    public static final class PassengerBuilder implements IPassengerBuilder{

        private long id;
        private String firstName;
        private String lastName;
        private String gender;
        private BookingRecord bookingRecord;

        public PassengerBuilder withId(long id){
            this.id = id;
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

        public PassengerBuilder withBookingRecord(BookingRecord bookingRecord){
            this.bookingRecord = bookingRecord;
            return self();
        }

        @Override
        public Passenger build(){
            return new Passenger(this);
        }

        private PassengerBuilder self(){
            return this;
        }
    }

}
