package com.koushik.reservation.utilities;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.model.ReservationGuestDetails;


public interface Converter {

	public ReservationDetails convert(ReservationDetailsDto reservationDetailsDto);
	public ReservationGuestDetails convertGuest(ReservationDetails reservationDetails);
}
