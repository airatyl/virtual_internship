package org.airat.travel.insurance.core.validation.person;

import org.airat.travel.insurance.core.validation.RequestFieldValidation;
import org.airat.travel.insurance.dto.v2.PersonDTO;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AgeNotInFutureValidation implements RequestFieldValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {

        for (PersonDTO person : request.getPeople()) {
            if (person.getBirthDate()!=null &&
                    LocalDate.now().until(person.getBirthDate()).getDays() >= 0) {
                return Optional.of(new ValidationError("birthDate","Дата рождения должна быть в прошлом"));
            }
        }
        return Optional.empty();
    }
}
