package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class AgreementDateToNotEmptyValidation implements RequestFieldValidation{
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }
}
