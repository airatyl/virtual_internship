package org.airat.travel.insurance.core.validation.person;

import org.airat.travel.insurance.core.validation.RequestFieldValidation;
import org.airat.travel.insurance.dto.v2.Person;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonLastNameValidation implements RequestFieldValidation {

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        for (Person person : request.getPeople()) {
            if (person.getPersonLastName() == null || person.getPersonLastName().isEmpty()) {
                return Optional.of(new ValidationError("personLastName", "Должно быть заполнено"));
            }
        }
        return Optional.empty();
    }
}
