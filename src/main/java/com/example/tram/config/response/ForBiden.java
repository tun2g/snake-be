package com.example.tram.config.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForBiden extends  RuntimeException{
    public ForBiden(String message) {
        super(message);
    }
}
