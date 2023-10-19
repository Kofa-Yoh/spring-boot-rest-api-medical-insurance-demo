package com.example.restApiMedicalInsurance.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;
    private String debugMessage;
    private Collection<T> data;

    public ApiResponse(HttpStatus status, String debugMessage, Collection<T> data) {
        this.status = status;
        this.debugMessage = debugMessage;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
        this.message = "data size: " + data.size() + " elements";
    }
}
