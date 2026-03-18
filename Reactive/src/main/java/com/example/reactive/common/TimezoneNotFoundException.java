package com.example.reactive.common;

import java.net.URI;

public class TimezoneNotFoundException extends RuntimeException {
    public TimezoneNotFoundException(String message) {
        super(message);
    }
}
