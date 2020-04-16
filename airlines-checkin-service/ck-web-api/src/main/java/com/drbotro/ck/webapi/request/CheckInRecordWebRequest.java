package com.drbotro.ck.webapi.request;

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
public class CheckInRecordWebRequest extends AbstractModelBean{

    private long id;
    private String lastName;
    private String firstName;
    private String seatNumber;
    private Date checkInTime;
    private String flightNumber;
    private String flightDate;
    private long bookingId;

    private CheckInRecordWebRequest(CheckInRecordWebRequestBuilder builder){
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.seatNumber = builder.seatNumber;
        this.checkInTime = builder.checkInTime;
        this.flightNumber = builder.flightNumber;
        this.flightDate = builder.flightDate;
        this.bookingId = builder.bookingId;
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
        if(!(other instanceof CheckInRecordWebRequest)){
            return false;
        }
        final CheckInRecordWebRequest castOther = (CheckInRecordWebRequest) other;
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

    public static CheckInRecordWebRequestBuilder builder(){
        return new CheckInRecordWebRequestBuilder();
    }

    public CheckInRecordWebRequestBuilder cloneBuilder(){
        return new CheckInRecordWebRequestBuilder().withLastName(this.lastName).withFirstName(this.firstName)
                .withSeatNumber(this.seatNumber).withCheckInTime(this.checkInTime).withFlightNumber(this.flightNumber)
                .withFlightDate(this.flightDate).withBookingId(this.bookingId);
    }

    public interface ICheckInRecordWebRequestBuilder{
        CheckInRecordWebRequest build();
    }

    public static final class CheckInRecordWebRequestBuilder implements ICheckInRecordWebRequestBuilder{

        private String lastName;
        private String firstName;
        private String seatNumber;
        private Date checkInTime;
        private String flightNumber;
        private String flightDate;
        private long bookingId;

        public CheckInRecordWebRequestBuilder withLastName(String lastName){
            this.lastName = lastName;
            return self();
        }

        public CheckInRecordWebRequestBuilder withFirstName(String firstName){
            this.firstName = firstName;
            return self();
        }

        public CheckInRecordWebRequestBuilder withSeatNumber(String seatNumber){
            this.seatNumber = seatNumber;
            return self();
        }

        public CheckInRecordWebRequestBuilder withCheckInTime(Date checkInTime){
            this.checkInTime = checkInTime;
            return self();
        }

        public CheckInRecordWebRequestBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public CheckInRecordWebRequestBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public CheckInRecordWebRequestBuilder withBookingId(long bookingId){
            this.bookingId = bookingId;
            return self();
        }

        @Override
        public CheckInRecordWebRequest build(){
            return new CheckInRecordWebRequest(this);
        }

        private CheckInRecordWebRequestBuilder self(){
            return this;
        }
    }

}
