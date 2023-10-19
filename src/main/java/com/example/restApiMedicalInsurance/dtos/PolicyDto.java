package com.example.restApiMedicalInsurance.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Policy Entity")
public class PolicyDto {

    private String series;

    private String code;

    private PolicyType docType;

    private LocalDate issuedDate;

    private LocalDate startDate;

    private LocalDate expiredDate;

    @Schema(description = "0 - not main, 1 - main", allowableValues = {"0", "1"})
    private Byte isMain;
}
