package com.example.restApiMedicalInsurance.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Person Entity")
public class PersonDto {
    private String lastname;
    private String firstname;
    private String secondname;
    @Schema(description = "0 - male, 1 - female", allowableValues = {"0", "1"})
    private Integer sex;
    @Schema(format = "yyyy-mm-dd")
    private LocalDate birthdate;
    @Schema(format = "000-000-000 00")
    private String snils;
    private PolicyDto mainPolicy;
}
