package com.stackroute.muzix.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobbalExecptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {TrackAlreadyExistsException.class})
    protected ResponseEntity<?> handleTrackAlreadyFoundExeption(TrackAlreadyExistsException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {TrackNotFoundException.class})
    protected ResponseEntity<?> handleTrackNotFoundException(TrackAlreadyExistsException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {HttpServerErrorException.class})
    protected ResponseEntity<?> handleInternalServerError(HttpServerErrorException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<?> generalException(Exception ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
