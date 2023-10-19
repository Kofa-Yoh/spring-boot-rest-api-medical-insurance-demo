package com.example.restApiMedicalInsurance.err;

public class NoPersonsFoundException extends Throwable {

    public NoPersonsFoundException() {
        super("No patients found.");
    }
}
