package com.hotel.guestservice.common;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class GuestApiResponse<T> {
    HttpStatus status;
    String message;
    T data;
}
