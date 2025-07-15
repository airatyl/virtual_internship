package org.airat.travel.insurance.core.validation.person;

import org.airat.travel.insurance.core.validation.RequestFieldValidation;
import org.airat.travel.insurance.dto.ValidationError;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonCodeNotEmptyValidation implements RequestFieldValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        for (PersonDTO person : request.getPeople()) {
            if (person.getPersonCode() == null || person.getPersonCode().isEmpty()) {
                return Optional.of(new ValidationError("personCode", "Должен быть заполнен"));
            }
        }
        return Optional.empty();
    }
}
