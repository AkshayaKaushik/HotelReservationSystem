package com.hotel.guestservice.exceptions;

import com.hotel.guestservice.common.GuestApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public GuestApiResponse<String> handleRecordNotFoundException(RecordNotFoundException ex){
        log.error(ex.toString());
        return new GuestApiResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR,"RECORD NOT FOUND",null);
    }

    @ExceptionHandler(value={DatabaseBusinessException.class})
    public GuestApiResponse<String> handleDatabaseBusinessException(DatabaseBusinessException ex){
        log.error(ex.toString());
        return new GuestApiResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR,"Error while processing request in database layer:\n "+ex.getLocalizedMessage(),null);
    }

    @ExceptionHandler(value ={ServiceBusinessException.class})
    public GuestApiResponse<String> handleServiceBusinessException(ServiceBusinessException ex){
        log.error(ex.toString());
        return new GuestApiResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR,"Error while processing request in service layer:\n "+ex.getLocalizedMessage(),null);
    }

    @ExceptionHandler(value = {Exception.class})
    public GuestApiResponse<String> handleException(Exception ex){
        log.error(ex.toString());
        return new GuestApiResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR,ex.getLocalizedMessage(), null);
    }
}
