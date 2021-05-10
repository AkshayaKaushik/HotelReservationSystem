package com.koushik.reservation.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationPaymentDetails {
    @JMap
    private String name;
    @JMap
    private int hotelId;
    @JMap
    private RoomTypes roomType;
    @JMap
    private Date bookingDate;
}
