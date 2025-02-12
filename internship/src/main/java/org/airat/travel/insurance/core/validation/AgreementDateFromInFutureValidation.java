package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AgreementDateFromInFutureValidation implements RequestFieldValidation{

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom()!=null &&
                LocalDate.now().until(request.getAgreementDateFrom()).getDays() < 0)
                ? Optional.of(new ValidationError("agreementDateFrom","agreementDateFrom not be in future"))
                : Optional.empty();
    }
}
