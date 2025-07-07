package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class PersonLastNameValidation implements RequestFieldValidation {

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Должно быть заполнено"))
                : Optional.empty();
    }
}
