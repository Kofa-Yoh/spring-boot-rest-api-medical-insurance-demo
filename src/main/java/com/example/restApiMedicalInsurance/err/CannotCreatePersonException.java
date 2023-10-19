package com.example.restApiMedicalInsurance.err;

public class CannotCreatePersonException extends Throwable {

    public CannotCreatePersonException() {
        super("Patient wasn't created.");
    }
}
