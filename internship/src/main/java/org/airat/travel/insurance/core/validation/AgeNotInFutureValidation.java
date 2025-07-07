package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AgeNotInFutureValidation implements RequestFieldValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getBirthDate()!=null &&
                LocalDate.now().until(request.getBirthDate()).getDays() >= 0)
                ? Optional.of(new ValidationError("birthDate","Дата рождения должна быть в прошлом"))
                : Optional.empty();
    }
}
