package com.drbotro.bk.webapi.request;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
public class BookingRecordWebRequest extends AbstractModelBean{

    private long bookingRecordWebRequestId;
    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;
    private Date bookingDate;
    private String fare;
    private String status;

    private List<PassengerWebRequest> passengersWebRequest;

    private BookingRecordWebRequest(BookingRecordWebRequestBuilder builder){
        this.bookingRecordWebRequestId = builder.bookingRecordWebRequestId;
        this.flightNumber = builder.flightNumber;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
        this.bookingDate = builder.bookingDate;
        this.fare = builder.fare;
        this.status = builder.status;
        this.passengersWebRequest = builder.passengersWebRequest;
    }

    public long getBookingRecordWebRequestId(){
        return bookingRecordWebRequestId;
    }

    public String getFlightNumber(){
        return flightNumber;
    }

    public String getOrigin(){
        return origin;
    }

    public String getDestination(){
        return destination;
    }

    public String getFlightDate(){
        return flightDate;
    }

    public Date getBookingDate(){
        return bookingDate;
    }

    public String getFare(){
        return fare;
    }

    public String getStatus(){
        return status;
    }

    public List<PassengerWebRequest> getPassengersWebRequest(){
        return passengersWebRequest;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof BookingRecordWebRequest)){
            return false;
        }
        final BookingRecordWebRequest castOther = (BookingRecordWebRequest) other;
        return new EqualsBuilder().append(bookingRecordWebRequestId, castOther.bookingRecordWebRequestId)
                .append(flightNumber, castOther.flightNumber).append(origin, castOther.origin)
                .append(destination, castOther.destination).append(flightDate, castOther.flightDate)
                .append(bookingDate, castOther.bookingDate).append(fare, castOther.fare)
                .append(status, castOther.status).append(passengersWebRequest, castOther.passengersWebRequest)
                .isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(bookingRecordWebRequestId).append(flightNumber).append(origin)
                .append(destination).append(flightDate).append(bookingDate).append(fare).append(status)
                .append(passengersWebRequest).toHashCode();
    }

    public static BookingRecordWebRequestBuilder builder(){
        return new BookingRecordWebRequestBuilder();
    }

    public BookingRecordWebRequestBuilder cloneBuilder(){
        return new BookingRecordWebRequestBuilder().withBookingRecordWebRequestId(this.bookingRecordWebRequestId)
                .withFlightNumber(this.flightNumber).withOrigin(this.origin).withDestination(this.destination)
                .withFlightDate(this.flightDate).withBookingDate(this.bookingDate).withFare(this.fare)
                .withStatus(this.status).withPassengersWebRequest(this.passengersWebRequest);
    }

    public interface IBookingRecordWebRequestBuilder{
        BookingRecordWebRequest build();
    }

    public static final class BookingRecordWebRequestBuilder implements IBookingRecordWebRequestBuilder{

        private long bookingRecordWebRequestId;
        private String flightNumber;
        private String origin;
        private String destination;
        private String flightDate;
        private Date bookingDate;
        private String fare;
        private String status;
        private List<PassengerWebRequest> passengersWebRequest = Collections.emptyList();

        private BookingRecordWebRequestBuilder(){
        }

        public BookingRecordWebRequestBuilder withBookingRecordWebRequestId(long bookingRecordWebRequestId){
            this.bookingRecordWebRequestId = bookingRecordWebRequestId;
            return self();
        }

        public BookingRecordWebRequestBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public BookingRecordWebRequestBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public BookingRecordWebRequestBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public BookingRecordWebRequestBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public BookingRecordWebRequestBuilder withBookingDate(Date bookingDate){
            this.bookingDate = bookingDate;
            return self();
        }

        public BookingRecordWebRequestBuilder withFare(String fare){
            this.fare = fare;
            return self();
        }

        public BookingRecordWebRequestBuilder withStatus(String status){
            this.status = status;
            return self();
        }

        public BookingRecordWebRequestBuilder withPassengersWebRequest(List<PassengerWebRequest> passengersWebRequest){
            this.passengersWebRequest = passengersWebRequest;
            return self();
        }

        @Override
        public BookingRecordWebRequest build(){
            return new BookingRecordWebRequest(this);
        }

        private BookingRecordWebRequestBuilder self(){
            return this;
        }
    }

}
