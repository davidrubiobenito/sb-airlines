package com.drbotro.bk.repository.model.v2;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.drbotro.bk.common.model.AbstractModelBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "booking_record_booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRecordBookingV2 extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_record_id")
    private long bookingRecordId;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "flight_date")
    private String flightDate;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "fare")
    private String fare;

    @Column(name = "status")
    private String status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "booking_passenger_join", joinColumns = @JoinColumn(name = "booking_record_id"), inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<PassengerBookingV2> passengers;

    private BookingRecordBookingV2(BookingRecordBuilder builder){
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

    public List<PassengerBookingV2> getPassengers(){
        return passengers;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof BookingRecordBookingV2)){
            return false;
        }
        final BookingRecordBookingV2 castOther = (BookingRecordBookingV2) other;
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
        BookingRecordBookingV2 build();
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
        private List<PassengerBookingV2> passengers = Collections.emptyList();

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

        public BookingRecordBuilder withPassengers(List<PassengerBookingV2> passengers){
            this.passengers = passengers;
            return self();
        }

        @Override
        public BookingRecordBookingV2 build(){
            return new BookingRecordBookingV2(this);
        }

        private BookingRecordBuilder self(){
            return this;
        }
    }

}
