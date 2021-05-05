package com.hotel.guestservice.handlers;

import com.hotel.guestservice.common.GuestApiResponse;
import com.hotel.guestservice.services.GuestService;
import com.hotel.guestservice.services.dto.GuestCompleteDetailEntity;
import com.hotel.guestservice.services.dto.GuestServiceDomainEntity;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/guestservice")
@CircuitBreaker(name = "guestCircuitBreaker", fallbackMethod = "remoteCallFail")
public class GuestServiceControllerImplementation implements GuestServiceController {
    @Autowired
    private GuestService service;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("%{spring.application.name:guest_service}")
    private String applicationName;

    @PostMapping("/register")
    public GuestApiResponse<GuestCompleteDetailEntity> registerGuest(@RequestBody GuestServiceDomainEntity newGuest) throws Exception {

        GuestApiResponse<GuestCompleteDetailEntity> response = new GuestApiResponse<>();
        response.setStatus(HttpStatus.OK);
        response.setData(service.addGuestDetails(newGuest));
        response.setMessage("Successfully created the user");
        log.info("Successfully created the guest = " + newGuest.getName());
        return response;
    }

    @GetMapping("/guest")
    public GuestApiResponse<GuestCompleteDetailEntity> getGuestDetails(@RequestParam String name, @RequestParam String password) throws Exception {
        GuestApiResponse<GuestCompleteDetailEntity> response = new GuestApiResponse<>();
        response.setStatus(HttpStatus.OK);
        response.setData(service.getCompleteGuestDetails(name, password));
        response.setMessage("Successfully fetched the details");
        log.info("Successfully retrieved the details of guest = " + name);
        return response;
    }

    @GetMapping("/reservation")
    public GuestApiResponse<GuestCompleteDetailEntity> getReservationDetails(@RequestParam String name, @RequestParam String password) throws Exception {
        GuestApiResponse<GuestCompleteDetailEntity> response = new GuestApiResponse<>();
        response.setStatus(HttpStatus.OK);
        response.setData(service.getGuestReservationDetails(name, password));
        response.setMessage("Successfully fetched the details");

        log.info("Successfully retrieved the reservation details of guest = " + name);
        return response;

    }


    public GuestApiResponse<String> remoteCallFail(Exception e) {
        GuestApiResponse<String> response = new GuestApiResponse<>();
        response.setMessage("Guest Service not available please avoid transactions");
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        response.setData(null);
        return response;
    }

}
