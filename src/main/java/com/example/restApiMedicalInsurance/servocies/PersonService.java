package com.example.restApiMedicalInsurance.servocies;

import com.example.restApiMedicalInsurance.dtos.PersonDto;
import com.example.restApiMedicalInsurance.dtos.PersonPayload;
import com.example.restApiMedicalInsurance.err.CannotCreatePersonException;
import com.example.restApiMedicalInsurance.err.CannotProcessEnteredParametersException;
import com.example.restApiMedicalInsurance.models.Person;
import com.example.restApiMedicalInsurance.repositories.PersonRepository;
import com.example.restApiMedicalInsurance.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public PersonDto addPerson(PersonPayload personPayload) throws CannotProcessEnteredParametersException, CannotCreatePersonException {
        Person person = MappingUtils.mapToPerson(personPayload);
        Person newPerson = null;
        if (person == null) {
            throw new CannotProcessEnteredParametersException();
        } else {
            newPerson = personRepository.save(person);
        }
        if (newPerson == null) {
            throw new CannotCreatePersonException();
        }
        return MappingUtils.mapToPersonDto(newPerson);
    }

    public PersonDto getPersonById(Long id) {
        return MappingUtils.mapToPersonDto(personRepository.findPersonById(id));
    }

    public Page<PersonDto> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable)
                .map(MappingUtils::mapToPersonDto);
    }

    public List<PersonDto> getPersonsByName(String text) {
        return personRepository.findPersonsByLastnameStartsWithIgnoreCaseOrFirstnameStartsWithIgnoreCaseOrSecondnameStartsWithIgnoreCaseOrderByLastname(text, text, text)
                .stream()
                .map(MappingUtils::mapToPersonDto)
                .toList();
    }

    public List<PersonDto> getPersonsByAgeAndOlder(int age) {
        LocalDate from = LocalDate.from(LocalDate.now().minusYears(age));
        return personRepository.findPersonsByBirthdateIsLessThanEqual(from).stream()
                .map(MappingUtils::mapToPersonDto)
                .toList();
    }

    public PersonDto getPersonByPolicy(String series, String code) {
        return MappingUtils.mapToPersonDto(personRepository.findPersonByPolicyList_SeriesAndPolicyList_Code(series, code));
    }

    public List<PersonDto> getPersonsWithTemporaryPolicy() {
        return personRepository.getPersonsWithTemporaryPolicy()
                .stream()
                .map(MappingUtils::mapToPersonDto)
                .toList();
    }

    public List<PersonDto> getPersonsWithSeveralPolicies() {
        return personRepository.getPersonsWithSeveralPolicies()
                .stream()
                .map(MappingUtils::mapToPersonDto)
                .toList();
    }
}
