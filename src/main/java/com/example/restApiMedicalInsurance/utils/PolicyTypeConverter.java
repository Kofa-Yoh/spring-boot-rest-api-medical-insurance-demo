package com.example.restApiMedicalInsurance.utils;

import com.example.restApiMedicalInsurance.dtos.PolicyType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PolicyTypeConverter implements AttributeConverter<PolicyType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PolicyType policyType) {
        if (policyType == null) {
            return null;
        }
        return policyType.getCode();
    }

    @Override
    public PolicyType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Stream.of(PolicyType.values())
                .filter(v -> v.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
