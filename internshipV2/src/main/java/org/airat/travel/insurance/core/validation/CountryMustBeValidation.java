package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CountryMustBeValidation implements RequestFieldValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        return (request.getCountry() != null && !request.getCountry().isEmpty())
                ? Optional.empty()
                : Optional.of(new ValidationError("country", "Должен быть заполнен"));

    }
}
