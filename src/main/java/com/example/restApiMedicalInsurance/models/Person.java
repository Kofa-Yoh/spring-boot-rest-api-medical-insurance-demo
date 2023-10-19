package com.example.restApiMedicalInsurance.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastname;

    private String firstname;

    private String secondname;

    @Column(nullable = false)
    private Byte sex;

    private LocalDate birthdate;

    @Column(length = 14)
    private String snils;

    @OneToMany(mappedBy = "person")
    private List<Policy> policyList = new ArrayList<>();
}
