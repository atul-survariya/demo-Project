package com.scaler.demo.advices;

import com.scaler.demo.dtos.ErrorDto;
import com.scaler.demo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNPException(){
        ErrorDto dto= new ErrorDto();
        dto.setMessage("Something went wrong");
        ResponseEntity<ErrorDto> response = new ResponseEntity<>(dto, HttpStatusCode.valueOf(501));
        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(){
        ErrorDto dto= new ErrorDto();
        dto.setMessage("Product not found Please try again");
        ResponseEntity<ErrorDto> response = new ResponseEntity<>(dto, HttpStatusCode.valueOf(404));
        return response;

    }

    @ExceptionHandler(Exception.class)
    public void handleAllExceptions(){

    }
}
