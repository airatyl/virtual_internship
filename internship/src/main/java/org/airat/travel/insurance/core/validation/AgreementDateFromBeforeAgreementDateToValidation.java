package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class AgreementDateFromBeforeAgreementDateToValidation implements RequestFieldValidation {


    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom()!=null && request.getAgreementDateTo()!=null &&
                request.getAgreementDateFrom().until(request.getAgreementDateTo()).getDays() < 0)
                ? Optional.of(new ValidationError("agreementDates","agreementDateFrom should be no earlier than agreementDateTo"))
                : Optional.empty();
    }
}
