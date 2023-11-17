package com.example.tram.config.response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

    public static ResponseEntity<?> SuccessResponse(Object data, String message){

        ApiResponse response = ApiResponse.builder()
            .statusCode(HttpStatus.OK.value())
            .message(message)
            .data(data)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<?> CreatedResponse(Object data, String message){
        ApiResponse response = ApiResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
