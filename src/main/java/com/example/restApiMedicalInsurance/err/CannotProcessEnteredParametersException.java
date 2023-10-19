package com.example.restApiMedicalInsurance.err;

public class CannotProcessEnteredParametersException extends Throwable {

    public CannotProcessEnteredParametersException() {
        super("Check parameters.");
    }
}
