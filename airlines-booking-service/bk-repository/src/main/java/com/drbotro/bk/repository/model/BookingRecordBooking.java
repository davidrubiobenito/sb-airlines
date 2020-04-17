package com.drbotro.bk.repository.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "bk_booking_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRecordBooking extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingRecordId;

    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;
    private Date bookingDate;
    private String fare;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingRecord", fetch = FetchType.LAZY)
    private List<PassengerBooking> passengers;

    private BookingRecordBooking(BookingRecordBuilder builder){
        this.bookingRecordId = builder.bookingRecordId;
        this.flightNumber = builder.flightNumber;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
        this.bookingDate = builder.bookingDate;
        this.fare = builder.fare;
        this.status = builder.status;
        this.passengers = builder.passengers;
    }

    public long getBookingRecordId(){
        return bookingRecordId;
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

    @JsonManagedReference
    public List<PassengerBooking> getPassengers(){
        return passengers;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof BookingRecordBooking)){
            return false;
        }
        final BookingRecordBooking castOther = (BookingRecordBooking) other;
        return new EqualsBuilder().append(bookingRecordId, castOther.bookingRecordId)
                .append(flightNumber, castOther.flightNumber).append(origin, castOther.origin)
                .append(destination, castOther.destination).append(flightDate, castOther.flightDate)
                .append(bookingDate, castOther.bookingDate).append(fare, castOther.fare)
                .append(status, castOther.status).append(passengers, castOther.passengers).isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(bookingRecordId).append(flightNumber).append(origin).append(destination)
                .append(flightDate).append(bookingDate).append(fare).append(status).append(passengers).toHashCode();
    }

    public static BookingRecordBuilder builder(){
        return new BookingRecordBuilder();
    }

    public BookingRecordBuilder cloneBuilder(){
        return new BookingRecordBuilder().withBookingRecordId(this.bookingRecordId).withFlightNumber(this.flightNumber)
                .withOrigin(this.origin).withDestination(this.destination).withFlightDate(this.flightDate)
                .withBookingDate(this.bookingDate).withFare(this.fare).withStatus(this.status)
                .withPassengers(this.passengers);
    }

    public interface IBookingRecordBuilder{
        BookingRecordBooking build();
    }

    public static final class BookingRecordBuilder implements IBookingRecordBuilder{

        private long bookingRecordId;
        private String flightNumber;
        private String origin;
        private String destination;
        private String flightDate;
        private Date bookingDate;
        private String fare;
        private String status;
        private List<PassengerBooking> passengers = Collections.emptyList();

        private BookingRecordBuilder(){
        }

        public BookingRecordBuilder withBookingRecordId(long bookingRecordId){
            this.bookingRecordId = bookingRecordId;
            return self();
        }

        public BookingRecordBuilder withFlightNumber(String flightNumber){
            this.flightNumber = flightNumber;
            return self();
        }

        public BookingRecordBuilder withOrigin(String origin){
            this.origin = origin;
            return self();
        }

        public BookingRecordBuilder withDestination(String destination){
            this.destination = destination;
            return self();
        }

        public BookingRecordBuilder withFlightDate(String flightDate){
            this.flightDate = flightDate;
            return self();
        }

        public BookingRecordBuilder withBookingDate(Date bookingDate){
            this.bookingDate = bookingDate;
            return self();
        }

        public BookingRecordBuilder withFare(String fare){
            this.fare = fare;
            return self();
        }

        public BookingRecordBuilder withStatus(String status){
            this.status = status;
            return self();
        }

        public BookingRecordBuilder withPassengers(List<PassengerBooking> passengers){
            this.passengers = passengers;
            return self();
        }

        @Override
        public BookingRecordBooking build(){
            return new BookingRecordBooking(this);
        }

        private BookingRecordBuilder self(){
            return this;
        }
    }

}
