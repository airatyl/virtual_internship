package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgeNotEmptyValidation implements RequestFieldValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getBirthDate() != null)
                ? Optional.empty()
                : Optional.of(new ValidationError("birthDate", "Должен быть заполнен"));

    }
}
