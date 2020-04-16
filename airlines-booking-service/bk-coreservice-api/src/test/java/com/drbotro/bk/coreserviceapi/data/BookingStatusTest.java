package com.drbotro.bk.coreserviceapi.data;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class BookingStatusTest{

    public static final String BOOKING_CONFIRMED = "BOOKING_CONFIRMED";
    public static final String CHECKED_IN = "CHECKED_IN";

    @Test
    public void shuoldReturnBookingConfirmed(){
        assertThat(BookingStatus.BOOKING_CONFIRMED, is(BOOKING_CONFIRMED));
    }

    @Test
    public void shuoldReturnCheckIn(){
        assertThat(BookingStatus.CHECKED_IN, is(CHECKED_IN));
    }

}
