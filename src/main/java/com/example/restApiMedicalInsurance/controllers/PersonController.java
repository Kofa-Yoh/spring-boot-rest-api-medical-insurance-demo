package com.example.restApiMedicalInsurance.controllers;

import com.example.restApiMedicalInsurance.dtos.PersonDto;
import com.example.restApiMedicalInsurance.dtos.PersonPayload;
import com.example.restApiMedicalInsurance.err.CannotCreatePersonException;
import com.example.restApiMedicalInsurance.err.CannotProcessEnteredParametersException;
import com.example.restApiMedicalInsurance.err.NoPersonsFoundException;
import com.example.restApiMedicalInsurance.servocies.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Person's API")
public class PersonController {

    private final PersonService personService;

    @Operation(summary = "Add new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person was created"),
            @ApiResponse(responseCode = "400", description = "Person was not created")})
    @PostMapping("/new-person")
    public ResponseEntity<PersonDto> createPerson(@ParameterObject PersonPayload personPayload)
            throws CannotProcessEnteredParametersException, CannotCreatePersonException {
        return new ResponseEntity<>(personService.addPerson(personPayload), HttpStatus.CREATED);
    }

    @Operation(summary = "Find person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person with id"),
            @ApiResponse(responseCode = "404", description = "No persons found")})
    @GetMapping("/person/{id}")
    public ResponseEntity<com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>> getPersonById(
            @PathVariable("id") long id) throws NoPersonsFoundException {
        PersonDto personDto = personService.getPersonById(id);
        if (personDto == null) {
            throw new NoPersonsFoundException();
        } else {
            return getSuccessful_request(List.of(personDto));
        }
    }

    @Operation(summary = "Get all persons",
            description = "Returns a list of persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person's list"),
            @ApiResponse(responseCode = "404", description = "No persons found")})
    @GetMapping("/all-persons")
    public ResponseEntity<com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>> getAllPersons(@ParameterObject Pageable pageable) throws NoPersonsFoundException {
        List<PersonDto> persons = personService.getAllPersons(pageable).getContent();
        if (persons.size() == 0) {
            throw new NoPersonsFoundException();
        }
        return getSuccessful_request(persons);
    }

    @Operation(summary = "Find persons by starting text of lastname, firstname or secondname",
            description = "Returns a list of persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person list with chosen text in lastname, firstname or secondname"),
            @ApiResponse(responseCode = "404", description = "No persons found")})
    @GetMapping("/persons-by-names-start-text/{name}")
    public ResponseEntity<com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>> getPersonsByNamesStartText(
            @Parameter(description = "Enter text for search person with a such lastname, firstname or secondname", example = "ivan")
            @PathVariable("name") String name) throws NoPersonsFoundException {
        List<PersonDto> persons = personService.getPersonsByName(name);
        if (persons.size() == 0) {
            throw new NoPersonsFoundException();
        }
        return getSuccessful_request(persons);
    }

    @Operation(summary = "Get adult persons",
            description = "Returns a list of persons 18 years and older")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adult persons list 18+"),
            @ApiResponse(responseCode = "404", description = "No persons found")})
    @GetMapping("/persons-18-years-and-older")
    public ResponseEntity<com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>> getPersonsWith18AgeAndOlder()
            throws NoPersonsFoundException {
        List<PersonDto> persons = personService.getPersonsByAgeAndOlder(18);
        int size = persons.size();
        if (size == 0) {
            throw new NoPersonsFoundException();
        }
        return getSuccessful_request(persons);
    }

    @Operation(summary = "Find person by policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person with policy"),
            @ApiResponse(responseCode = "404", description = "No persons found")})
    @GetMapping("/person-by-policy")
    public ResponseEntity<com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>> getPersonByPolicy(
            @RequestParam(value = "series", required = false) String series,
            @RequestParam("code") String code) throws NoPersonsFoundException {
        PersonDto personDto = personService.getPersonByPolicy(series, code);
        if (personDto == null) {
            throw new NoPersonsFoundException();
        } else {
            return getSuccessful_request(List.of(personDto));
        }
    }

    @Operation(summary = "Get persons with temporary policy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persons with temporary policy"),
            @ApiResponse(responseCode = "404", description = "No persons found")})
    @GetMapping("/persons-with-temporary-policy")
    public ResponseEntity<com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>> getPersonsWithTemporaryPolicy()
            throws NoPersonsFoundException {
        List<PersonDto> persons = personService.getPersonsWithTemporaryPolicy();
        int size = persons.size();
        if (size == 0) {
            throw new NoPersonsFoundException();
        }
        return getSuccessful_request(persons);
    }

    @Operation(summary = "Get persons with several policies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persons with several policies"),
            @ApiResponse(responseCode = "404", description = "No persons found")})
    @GetMapping("/persons-with-several-policies")
    public ResponseEntity<com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>> getPersonsWithSeveralPolicies()
            throws NoPersonsFoundException {
        List<PersonDto> persons = personService.getPersonsWithSeveralPolicies();
        int size = persons.size();
        if (size == 0) {
            throw new NoPersonsFoundException();
        }
        return getSuccessful_request(persons);
    }

    private ResponseEntity<com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>> getSuccessful_request(List<PersonDto> list) {
        return ResponseEntity.ok(
                new com.example.restApiMedicalInsurance.dtos.ApiResponse<PersonDto>(
                        HttpStatus.OK,
                        "successful request",
                        list));
    }
}
