package com.example.restApiMedicalInsurance.utils;

import com.example.restApiMedicalInsurance.dtos.PersonDto;
import com.example.restApiMedicalInsurance.dtos.PersonPayload;
import com.example.restApiMedicalInsurance.dtos.PolicyDto;
import com.example.restApiMedicalInsurance.models.Person;
import com.example.restApiMedicalInsurance.models.Policy;
import org.modelmapper.ModelMapper;

public class MappingUtils {

    private static ModelMapper modelMapper = new ModelMapper();

    public static PersonDto mapToPersonDto(Person person) {
        if (person == null) return null;
        PersonDto personDto = modelMapper.map(person, PersonDto.class);
        if (person.getPolicyList().size() > 0) {
            personDto.setMainPolicy(MappingUtils.mapToPolicyDto(person.getPolicyList()
                    .stream()
                    .filter(policy -> policy.getIsMain() == 1)
                    .findFirst()
                    .get()));
        }
        return personDto;
    }

    public static Person mapToPerson(PersonDto personDto) {
        if (personDto == null) return null;
        return modelMapper.map(personDto, Person.class);
    }

    public static Person mapToPerson(PersonPayload personPayload) {
        if (personPayload == null) return null;
        return modelMapper.map(personPayload, Person.class);
    }

    private static PolicyDto mapToPolicyDto(Policy policy) {
        if (policy == null) return null;
        return modelMapper.map(policy, PolicyDto.class);
    }


}
