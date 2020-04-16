package com.drbotro.bk.coreserviceapi.data;

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
public class BookingRecordRequest extends AbstractModelBean{

    private long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;
    private Date bookingDate;
    private String fare;
    private String status;

    private List<PassengerRequest> passengersRequest;

    private BookingRecordRequest(BookingRecordRequestBuilder builder){
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
        this.bookingDate = builder.bookingDate;
        this.fare = builder.fare;
        this.status = builder.status;
        this.passengersRequest = builder.passengersRequest;
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

    public List<PassengerRequest> getPassengersRequest(){
        return passengersRequest;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof BookingRecordRequest)){
            return false;
        }
        final BookingRecordRequest castOther = (BookingRecordRequest) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(origin, castOther.origin).append(destination, castOther.destination)
                .append(flightDate, castOther.flightDate).append(bookingDate, castOther.bookingDate)
                .append(fare, castOther.fare).append(status, castOther.status)
                .append(passengersRequest, castOther.passengersRequest).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(origin).append(destination)
                .append(flightDate).append(bookingDate).append(fare).append(status).append(passengersRequest)
                .toHashCode();
    }

    public static BookingRecordRequestBuilder builder(){
        return new BookingRecordRequestBuilder();
    }

    public BookingRecordRequestBuilder cloneBuilder(){
        return new BookingRecordRequestBuilder().withId(this.id).withFlightNumber(this.flightNumber)
                .withOrigin(this.origin).withDestination(this.destination).withFlightDate(this.flightDate)
                .withBookingDate(this.bookingDate).withFare(this.fare).withStatus(this.status)
                .withPassengersRequest(this.passengersRequest);
    }

    public interface IBookingRecordRequestBuilder{
        BookingRecordRequest build();
    }

    public static final class BookingRecordRequestBuilder implements IBookingRecordRequestBuilder{

        private long id;
        private String flightNumber;
        private String origin;
        private String destination;
        private String flightDate;
        private Date bookingDate;
        private String fare;
        private String status;
        private List<PassengerRequest> passengersRequest = Collections.emptyList();

        private BookingRecordRequestBuilder(){
        }

        public BookingRecordRequestBuilder withId(long id){
            this.id = id;
            return self();
        }

        public BookingRecordRequestBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public BookingRecordRequestBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public BookingRecordRequestBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public BookingRecordRequestBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public BookingRecordRequestBuilder withBookingDate(Date bookingDate){
            this.bookingDate = bookingDate;
            return self();
        }

        public BookingRecordRequestBuilder withFare(String fare){
            this.fare = fare;
            return self();
        }

        public BookingRecordRequestBuilder withStatus(String status){
            this.status = status;
            return self();
        }

        public BookingRecordRequestBuilder withPassengersRequest(List<PassengerRequest> passengersRequest){
            this.passengersRequest = passengersRequest;
            return self();
        }

        @Override
        public BookingRecordRequest build(){
            return new BookingRecordRequest(this);
        }

        private BookingRecordRequestBuilder self(){
            return this;
        }
    }

}
