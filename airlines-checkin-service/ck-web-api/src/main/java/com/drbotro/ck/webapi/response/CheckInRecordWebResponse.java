package com.drbotro.ck.webapi.response;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.ck.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInRecordWebResponse extends AbstractModelBean{

    private long id;
    private String lastName;
    private String firstName;
    private String seatNumber;
    private Date checkInTime;
    private String flightNumber;
    private String flightDate;
    private long bookingId;

    private CheckInRecordWebResponse(CheckInRecordWebResponseBuilder builder){
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
        if(!(other instanceof CheckInRecordWebResponse)){
            return false;
        }
        final CheckInRecordWebResponse castOther = (CheckInRecordWebResponse) other;
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

    public static CheckInRecordWebResponseBuilder builder(){
        return new CheckInRecordWebResponseBuilder();
    }

    public CheckInRecordWebResponseBuilder cloneBuilder(){
        return new CheckInRecordWebResponseBuilder().withLastName(this.lastName).withFirstName(this.firstName)
                .withSeatNumber(this.seatNumber).withCheckInTime(this.checkInTime).withFlightNumber(this.flightNumber)
                .withFlightDate(this.flightDate).withBookingId(this.bookingId);
    }

    public interface ICheckInRecordWebResponseBuilder{
        CheckInRecordWebResponse build();
    }

    public static final class CheckInRecordWebResponseBuilder implements ICheckInRecordWebResponseBuilder{

        private long id;
        private String lastName;
        private String firstName;
        private String seatNumber;
        private Date checkInTime;
        private String flightNumber;
        private String flightDate;
        private long bookingId;

        public CheckInRecordWebResponseBuilder withId(Long id){
            this.id = id;
            return self();
        }

        public CheckInRecordWebResponseBuilder withLastName(String lastName){
            this.lastName = lastName;
            return self();
        }

        public CheckInRecordWebResponseBuilder withFirstName(String firstName){
            this.firstName = firstName;
            return self();
        }

        public CheckInRecordWebResponseBuilder withSeatNumber(String seatNumber){
            this.seatNumber = seatNumber;
            return self();
        }

        public CheckInRecordWebResponseBuilder withCheckInTime(Date checkInTime){
            this.checkInTime = checkInTime;
            return self();
        }

        public CheckInRecordWebResponseBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public CheckInRecordWebResponseBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public CheckInRecordWebResponseBuilder withBookingId(long bookingId){
            this.bookingId = bookingId;
            return self();
        }

        @Override
        public CheckInRecordWebResponse build(){
            return new CheckInRecordWebResponse(this);
        }

        private CheckInRecordWebResponseBuilder self(){
            return this;
        }
    }

}
