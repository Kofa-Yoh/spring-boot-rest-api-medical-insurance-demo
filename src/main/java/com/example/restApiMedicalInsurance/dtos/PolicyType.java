package com.example.restApiMedicalInsurance.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PolicyType {

    OLD(1), TEMPORARY(2), UNIFORM(3);

    private Integer code;
}
