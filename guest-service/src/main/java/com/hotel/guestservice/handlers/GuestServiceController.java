package com.hotel.guestservice.handlers;

import com.hotel.guestservice.common.GuestApiResponse;
import com.hotel.guestservice.services.dto.GuestCompleteDetailEntity;
import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface GuestServiceController {
    public GuestApiResponse<GuestCompleteDetailEntity> registerGuest(@RequestBody GuestServiceDomainEntity newGuest) throws Exception;
    public GuestApiResponse<GuestCompleteDetailEntity> getGuestDetails(@RequestParam String name, @RequestParam String password) throws Exception;
    public GuestApiResponse<GuestCompleteDetailEntity> getReservationDetails(@RequestParam String name,@RequestParam String password) throws Exception;
}
