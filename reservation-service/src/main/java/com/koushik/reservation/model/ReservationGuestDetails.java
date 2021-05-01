package com.koushik.reservation.model;

import com.googlecode.jmapper.annotations.JMap;
import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationGuestDetails {
    @JMap
    private Date date;
    @JMap
    private String hotelName;
}
