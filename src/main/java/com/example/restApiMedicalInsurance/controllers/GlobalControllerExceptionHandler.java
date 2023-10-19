package com.example.restApiMedicalInsurance.controllers;

import com.example.restApiMedicalInsurance.dtos.ApiResponse;
import com.example.restApiMedicalInsurance.dtos.PersonDto;
import com.example.restApiMedicalInsurance.err.CannotCreatePersonException;
import com.example.restApiMedicalInsurance.err.CannotProcessEnteredParametersException;
import com.example.restApiMedicalInsurance.err.NoPersonsFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(NoPersonsFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<PersonDto>> handleNoPatientsFoundException(NoPersonsFoundException ex) {
        return new ResponseEntity<>(new ApiResponse<>(
                HttpStatus.NOT_FOUND,
                ex.getLocalizedMessage(),
                new ArrayList<>()
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CannotCreatePersonException.class, CannotProcessEnteredParametersException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<PersonDto>> handleCannotCreatePersonException(Exception ex) {
        return new ResponseEntity<>(new ApiResponse<>(
                HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(),
                new ArrayList<>()
        ), HttpStatus.BAD_REQUEST);
    }
}
