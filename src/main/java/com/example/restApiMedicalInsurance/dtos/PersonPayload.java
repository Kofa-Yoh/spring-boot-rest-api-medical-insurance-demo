package com.example.restApiMedicalInsurance.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Person Payload")
public class PersonPayload {
    @NotBlank
    private String lastname;
    @NotBlank
    private String firstname;
    private String secondname;
    @Schema(description = "0 - male, 1 - female", allowableValues = {"0", "1"})
    @NotBlank
    private Integer sex;
    @Schema(format = "yyyy-mm-dd")
    @NotBlank
    private LocalDate birthdate;
    @Schema(format = "000-000-000 00")
    @NotBlank
    private String snils;
}
