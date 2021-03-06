package com.koushik.reservation.service;

import com.koushik.reservation.configuration.kafka.KafkaConfiguration;
import com.koushik.reservation.model.ReservationGuestDetails;
import com.koushik.reservation.model.ReservationPaymentDetails;
import com.koushik.reservation.utilities.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koushik.reservation.configuration.IFeignClientConfig;
import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.Hotel;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.repository.ReservationRepository;
import com.koushik.reservation.response.ApiResponse;
import com.koushik.reservation.utilities.ReservationDetailsUtility;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReservationService implements IReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	IFeignClientConfig client;

	@Autowired
	KafkaConfiguration config;

	@Override
	public ReservationDetails bookHotelWithDetails(ReservationDetailsDto reservationDetailsDto) {
		ReservationDetails reservationDetails = new ReservationDetailsUtility().convert(reservationDetailsDto);
		ApiResponse<Hotel> response = client.getHotelDetailsById(reservationDetailsDto.getHotelId());
		reservationDetails.setHotelName(response.getData().getName());
		reservationDetails.setCost((response.getData().getRoomDetails().stream()
				.filter(r -> reservationDetailsDto.getRoomType().equals(r.getRoomtypes())).findAny().orElse(null)
				.getPrice()) * reservationDetailsDto.getNumberofDays());
		System.out.println(reservationDetails.toString());
		ReservationPaymentDetails reservationPaymentDetails = new ReservationDetailsUtility().convertHotelToPaymentDetails(reservationDetailsDto);
		config.sendMessage(reservationPaymentDetails);
		log.info("Message sen t to the kafka topic "+reservationPaymentDetails);
		return reservationRepository.save(reservationDetails);
	}

	@Override
	public ReservationGuestDetails[] getReservationForGuest(String name) {
		List<String> id=  new ArrayList<String>();
		id.add(name);
		List<ReservationDetails> reservationDetails = reservationRepository.findAllById(id);
		ReservationGuestDetails[] result = new ReservationGuestDetails[reservationDetails.size()];
		Converter converter = new ReservationDetailsUtility();
		for(int i=0;i< result.length;i++){
			result[i]=converter.convertGuest(reservationDetails.get(i));
		}
		return result;
	}

}
