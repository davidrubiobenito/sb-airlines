package com.drbotro.ck.coreserviceapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.ck.common.model.AbstractModelBean;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chekin_record_chechin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInRecord extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "ckeck_in_time")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkInTime;
    @Column(name = "flight_number")
    private String flightNumber;
    @Column(name = "flight_date")
    private String flightDate;
    @Column(name = "booking_id")
    private long bookingId;

    private CheckInRecord(CheckInRecordBuilder builder){
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.seatNumber = builder.seatNumber;
        this.checkInTime = builder.checkInTime;
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.bookingId = builder.bookingId;
    }

    public Long getId(){
        return id;
    }

    public String getLastName(){
        return lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSeatNumber(){
        return seatNumber;
    }

    public Date getCheckInTime(){
        return checkInTime;
    }

    public String getFlightNumber(){
        return flightNumber;
    }

    public String getFlightDate(){
        return flightDate;
    }

    public long getBookingId(){
        return bookingId;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof CheckInRecord)){
            return false;
        }
        final CheckInRecord castOther = (CheckInRecord) other;
        return new EqualsBuilder().append(id, castOther.id).append(lastName, castOther.lastName)
                .append(lastName, castOther.lastName).append(seatNumber, castOther.seatNumber)
                .append(checkInTime, castOther.checkInTime).append(flightNumber, castOther.flightNumber)
                .append(flightDate, castOther.flightDate).append(bookingId, castOther.bookingId).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(lastName).append(seatNumber).append(checkInTime)
                .append(flightNumber).append(flightDate).append(bookingId).toHashCode();
    }

    public static CheckInRecordBuilder builder(){
        return new CheckInRecordBuilder();
    }

    public CheckInRecordBuilder cloneBuilder(){
        return new CheckInRecordBuilder().withLastName(this.lastName).withFirstName(this.firstName)
                .withSeatNumber(this.seatNumber).withCheckInTime(this.checkInTime).withFlightNumber(this.flightNumber)
                .withFlightDate(this.flightDate).withBookingId(this.bookingId);
    }

    public interface ICheckInRecordBuilder{
        CheckInRecord build();
    }

    public static final class CheckInRecordBuilder implements ICheckInRecordBuilder{

        private long id;
        private String lastName;
        private String firstName;
        private String seatNumber;
        private Date checkInTime;
        private String flightNumber;
        private String flightDate;
        private long bookingId;

        public CheckInRecordBuilder withId(long id){
            this.id = id;
            return self();
        }

        public CheckInRecordBuilder withLastName(String lastName){
            this.lastName = lastName;
            return self();
        }

        public CheckInRecordBuilder withFirstName(String firstName){
            this.firstName = firstName;
            return self();
        }

        public CheckInRecordBuilder withSeatNumber(String seatNumber){
            this.seatNumber = seatNumber;
            return self();
        }

        public CheckInRecordBuilder withCheckInTime(Date checkInTime){
            this.checkInTime = checkInTime;
            return self();
        }

        public CheckInRecordBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public CheckInRecordBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public CheckInRecordBuilder withBookingId(long bookingId){
            this.bookingId = bookingId;
            return self();
        }

        @Override
        public CheckInRecord build(){
            return new CheckInRecord(this);
        }

        private CheckInRecordBuilder self(){
            return this;
        }
    }

}
