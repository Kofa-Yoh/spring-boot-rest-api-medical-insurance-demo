package com.example.restApiMedicalInsurance.models;

import com.example.restApiMedicalInsurance.dtos.PolicyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String series;

    @Column(nullable = false, length = 16)
    private String code;

    @Column(nullable = false)
    private PolicyType docType;

    @Column(nullable = false)
    private LocalDate issuedDate;

    private LocalDate startDate;

    private LocalDate expiredDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "person_id", nullable = false)
    @JsonIgnore
    private Person person;

    @Column(nullable = false)
    private Byte isMain;
}
