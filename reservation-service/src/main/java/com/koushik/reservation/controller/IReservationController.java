package com.koushik.reservation.controller;

import com.koushik.reservation.model.ReservationGuestDetails;
import org.springframework.web.bind.annotation.*;

import com.koushik.reservation.entity.ReservationDetails;
import com.koushik.reservation.model.ReservationDetailsDto;
import com.koushik.reservation.response.ApiResponse;

import io.swagger.annotations.Api;

@Api(value = "ReservationController", description = "REST API to perform CRUD operations related to Hotel Entity")
@RestController
public interface IReservationController {

	@PostMapping("/reserve")
	public ApiResponse<ReservationDetails> bookHotelWithDetails(
			@RequestBody ReservationDetailsDto reservationDetailsDto);

	@GetMapping("reserve/{id}")
	public ApiResponse<ReservationDetails> getBookingDetails(@PathVariable int id);

	@GetMapping("/guest")
	public ApiResponse<ReservationGuestDetails[]> getReservationByGuest(@RequestParam String name);
}
