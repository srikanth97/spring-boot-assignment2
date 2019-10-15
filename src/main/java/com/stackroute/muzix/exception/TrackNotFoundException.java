package com.stackroute.muzix.exception;

public class TrackNotFoundException extends Exception {

    private String message;

    public TrackNotFoundException(String message) {
        this.message = message;
    }

    public TrackNotFoundException() {
    }
}
