package com.drbotro.bk.repository.model;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking_record_booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRecord extends AbstractModelBean{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_record_id")
    private long id;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bookingRecord")
    @Column(name = "passengers")
    private Set<Passenger> passengers;

    private BookingRecord(BookingRecordBuilder builder){
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.flightDate = builder.flightDate;
        this.bookingDate = builder.bookingDate;
        this.fare = builder.fare;
        this.status = builder.status;
        this.passengers = builder.passengers;
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

    public Set<Passenger> getPassengers(){
        return passengers;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof BookingRecord)){
            return false;
        }
        final BookingRecord castOther = (BookingRecord) other;
        return new EqualsBuilder().append(id, castOther.id).append(flightNumber, castOther.flightNumber)
                .append(origin, castOther.origin).append(destination, castOther.destination)
                .append(flightDate, castOther.flightDate).append(bookingDate, castOther.bookingDate)
                .append(fare, castOther.fare).append(status, castOther.status).append(passengers, castOther.passengers)
                .isEquals();
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(id).append(flightNumber).append(origin).append(destination)
                .append(flightDate).append(bookingDate).append(fare).append(status).append(passengers).toHashCode();
    }

    public static BookingRecordBuilder builder(){
        return new BookingRecordBuilder();
    }

    public BookingRecordBuilder cloneBuilder(){
        return new BookingRecordBuilder().withId(this.id).withFlightNumber(this.flightNumber).withOrigin(this.origin)
                .withDestination(this.destination).withFlightDate(this.flightDate).withBookingDate(this.bookingDate)
                .withFare(this.fare).withStatus(this.status).withPassengers(this.passengers);
    }

    public interface IBookingRecordBuilder{
        BookingRecord build();
    }

    public static final class BookingRecordBuilder implements IBookingRecordBuilder{

        private long id;
        private String flightNumber;
        private String origin;
        private String destination;
        private String flightDate;
        private Date bookingDate;
        private String fare;
        private String status;
        private Set<Passenger> passengers = Collections.emptySet();

        private BookingRecordBuilder(){
        }

        public BookingRecordBuilder withId(long id){
            this.id = id;
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

        public BookingRecordBuilder withPassengers(Set<Passenger> passengers){
            this.passengers = passengers;
            return self();
        }

        @Override
        public BookingRecord build(){
            return new BookingRecord(this);
        }

        private BookingRecordBuilder self(){
            return this;
        }
    }

}
