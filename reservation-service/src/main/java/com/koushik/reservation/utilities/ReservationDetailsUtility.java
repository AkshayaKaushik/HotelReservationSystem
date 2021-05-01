package com.koushik.reservation.utilities;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationGuestDetails;
import com.koushik.reservation.model.ReservationDetailsDto;

public class ReservationDetailsUtility implements Converter {
	JMapper<ReservationGuestDetails,ReservationDetails> jMapperGuest;

	JMapper<ReservationDetails, ReservationDetailsDto> jMapper;
	
	public ReservationDetailsUtility() {
		JMapperAPI api = new JMapperAPI()
				.add(JMapperAPI.mappedClass(ReservationDetails.class));
		
		jMapper = new JMapper<>(ReservationDetails.class, ReservationDetailsDto.class, api);
		jMapperGuest = new JMapper<>(ReservationGuestDetails.class,ReservationDetails.class);
	}

	@Override
	public ReservationDetails convert(ReservationDetailsDto reservationDetailsDto) {
		return jMapper.getDestination(reservationDetailsDto);
	}

	@Override
	public ReservationGuestDetails convertGuest(ReservationDetails reservationDetails) {
		return jMapperGuest.getDestination(reservationDetails);
	}

}
