package com.drbotro.bk.coreserviceapi.data;

public class BookingStatus{

    public static final String BOOKING_CONFIRMED = "BOOKING_CONFIRMED";
    public static final String CHECKED_IN = "CHECKED_IN";

    private BookingStatus(){

    }

    public static String getBookingConfirmed(){
        return BOOKING_CONFIRMED;
    }

    public static String getCheckedIn(){
        return CHECKED_IN;
    }

}
