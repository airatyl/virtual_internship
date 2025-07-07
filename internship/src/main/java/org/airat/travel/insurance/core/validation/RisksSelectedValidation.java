package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RisksSelectedValidation implements RequestFieldValidation{
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getRisks() ==null || request.getRisks().isEmpty())
                ? Optional.of(new ValidationError("risks","Должен быть выбран хотя бы один риск"))
                : Optional.empty();
    }
}
