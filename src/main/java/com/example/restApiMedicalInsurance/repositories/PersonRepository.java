package com.example.restApiMedicalInsurance.repositories;

import com.example.restApiMedicalInsurance.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findPersonById(Long id);

    List<Person> findPersonsByLastnameStartsWithIgnoreCaseOrFirstnameStartsWithIgnoreCaseOrSecondnameStartsWithIgnoreCaseOrderByLastname(
            String lastname, String firstname, String secondname);

    List<Person> findPersonsByBirthdateIsLessThanEqual(LocalDate date);

    Person findPersonByPolicyList_SeriesAndPolicyList_Code(String series, String code);

    @Query(value = "from Person p join p.policyList po where po.docType = com.example.restApiMedicalInsurance.dtos.PolicyType.TEMPORARY")
    List<Person> getPersonsWithTemporaryPolicy();

    @Query(nativeQuery = true,
            value = "SELECT * FROM person p WHERE (SELECT count(*) FROM policy po WHERE po.person_id = p.id) > 1")
    List<Person> getPersonsWithSeveralPolicies();
}
