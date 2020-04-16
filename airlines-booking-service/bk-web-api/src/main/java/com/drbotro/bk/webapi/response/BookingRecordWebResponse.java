package com.drbotro.bk.webapi.response;

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
public class BookingRecordWebResponse extends AbstractModelBean{

    private long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;
    private Date bookingDate;
    private String fare;
    private String status;

    private List<PassengerWebResponse> passengersWebResponse;

    private BookingRecordWebResponse(BookingRecordWebResponseBuilder builder){
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
        this.bookingDate = builder.bookingDate;
        this.fare = builder.fare;
        this.status = builder.status;
        this.passengersWebResponse = builder.passengersWebResponse;
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

    public List<PassengerWebResponse> getPassengersWebResponse(){
        return passengersWebResponse;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof BookingRecordWebResponse)){
            return false;
        }
        final BookingRecordWebResponse castOther = (BookingRecordWebResponse) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(origin, castOther.origin).append(destination, castOther.destination)
                .append(flightDate, castOther.flightDate).append(bookingDate, castOther.bookingDate)
                .append(fare, castOther.fare).append(status, castOther.status)
                .append(passengersWebResponse, castOther.passengersWebResponse).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(origin).append(destination)
                .append(flightDate).append(bookingDate).append(fare).append(status).append(passengersWebResponse)
                .toHashCode();
    }

    public static BookingRecordWebResponseBuilder builder(){
        return new BookingRecordWebResponseBuilder();
    }

    public BookingRecordWebResponseBuilder cloneBuilder(){
        return new BookingRecordWebResponseBuilder().withId(this.id).withFlightNumber(this.flightNumber)
                .withOrigin(this.origin).withDestination(this.destination).withFlightDate(this.flightDate)
                .withBookingDate(this.bookingDate).withFare(this.fare).withStatus(this.status)
                .withPassengersWebResponse(this.passengersWebResponse);
    }

    public interface IBookingRecordWebResponseBuilder{
        BookingRecordWebResponse build();
    }

    public static final class BookingRecordWebResponseBuilder implements IBookingRecordWebResponseBuilder{

        private long id;
        private String flightNumber;
        private String origin;
        private String destination;
        private String flightDate;
        private Date bookingDate;
        private String fare;
        private String status;
        private List<PassengerWebResponse> passengersWebResponse = Collections.emptyList();

        private BookingRecordWebResponseBuilder(){
        }

        public BookingRecordWebResponseBuilder withId(long id){
            this.id = id;
            return self();
        }

        public BookingRecordWebResponseBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public BookingRecordWebResponseBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public BookingRecordWebResponseBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public BookingRecordWebResponseBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public BookingRecordWebResponseBuilder withBookingDate(Date bookingDate){
            this.bookingDate = bookingDate;
            return self();
        }

        public BookingRecordWebResponseBuilder withFare(String fare){
            this.fare = fare;
            return self();
        }

        public BookingRecordWebResponseBuilder withStatus(String status){
            this.status = status;
            return self();
        }

        public BookingRecordWebResponseBuilder withPassengersWebResponse(
                List<PassengerWebResponse> passengersWebResponse){
            this.passengersWebResponse = passengersWebResponse;
            return self();
        }

        @Override
        public BookingRecordWebResponse build(){
            return new BookingRecordWebResponse(this);
        }

        private BookingRecordWebResponseBuilder self(){
            return this;
        }
    }

}
