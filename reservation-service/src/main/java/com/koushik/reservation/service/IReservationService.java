package com.koushik.reservation.service;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.model.ReservationGuestDetails;

public interface IReservationService {

	public ReservationDetails bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto);
	public ReservationGuestDetails[] getReservationForGuest(String name);
}
