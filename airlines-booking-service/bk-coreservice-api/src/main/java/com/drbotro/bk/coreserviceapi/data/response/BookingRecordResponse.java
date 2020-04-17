package com.drbotro.bk.coreserviceapi.data.response;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRecordResponse extends AbstractModelBean{

    private long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;
    private Date bookingDate;
    private String fare;
    private String status;

    private List<PassengerResponse> passengersResponse;

    private BookingRecordResponse(BookingRecordResponseBuilder builder){
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
        this.bookingDate = builder.bookingDate;
        this.fare = builder.fare;
        this.status = builder.status;
        this.passengersResponse = builder.passengersResponse;
    }

    public long getId(){
        return id;
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

    public List<PassengerResponse> getPassengersResponse(){
        return passengersResponse;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof BookingRecordResponse)){
            return false;
        }
        final BookingRecordResponse castOther = (BookingRecordResponse) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(origin, castOther.origin).append(destination, castOther.destination)
                .append(flightDate, castOther.flightDate).append(bookingDate, castOther.bookingDate)
                .append(fare, castOther.fare).append(status, castOther.status)
                .append(passengersResponse, castOther.passengersResponse).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(origin).append(destination)
                .append(flightDate).append(bookingDate).append(fare).append(status).append(passengersResponse)
                .toHashCode();
    }

    public static BookingRecordResponseBuilder builder(){
        return new BookingRecordResponseBuilder();
    }

    public BookingRecordResponseBuilder cloneBuilder(){
        return new BookingRecordResponseBuilder().withId(this.id).withFlightNumber(this.flightNumber)
                .withOrigin(this.origin).withDestination(this.destination).withFlightDate(this.flightDate)
                .withBookingDate(this.bookingDate).withFare(this.fare).withStatus(this.status)
                .withPassengersResponse(this.passengersResponse);
    }

    public interface IBookingRecordResponseBuilder{
        BookingRecordResponse build();
    }

    public static final class BookingRecordResponseBuilder implements IBookingRecordResponseBuilder{

        private long id;
        private String flightNumber;
        private String origin;
        private String destination;
        private String flightDate;
        private Date bookingDate;
        private String fare;
        private String status;
        private List<PassengerResponse> passengersResponse = Collections.emptyList();

        private BookingRecordResponseBuilder(){
        }

        public BookingRecordResponseBuilder withId(long id){
            this.id = id;
            return self();
        }

        public BookingRecordResponseBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public BookingRecordResponseBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public BookingRecordResponseBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public BookingRecordResponseBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public BookingRecordResponseBuilder withBookingDate(Date bookingDate){
            this.bookingDate = bookingDate;
            return self();
        }

        public BookingRecordResponseBuilder withFare(String fare){
            this.fare = fare;
            return self();
        }

        public BookingRecordResponseBuilder withStatus(String status){
            this.status = status;
            return self();
        }

        public BookingRecordResponseBuilder withPassengersResponse(List<PassengerResponse> passengersResponse){
            this.passengersResponse = passengersResponse;
            return self();
        }

        @Override
        public BookingRecordResponse build(){
            return new BookingRecordResponse(this);
        }

        private BookingRecordResponseBuilder self(){
            return this;
        }
    }

}
