package org.airat.travel.insurance.core.repositories;

import org.airat.travel.insurance.core.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByFirstNameAndLastNameAndPersonCode(String firstName, String lastName, String personCode);
}
