package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class AgreementDateFromBeforeAgreementDateToValidation implements RequestFieldValidation {


    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        return (request.getAgreementDateFrom()!=null && request.getAgreementDateTo()!=null &&
                request.getAgreementDateFrom().until(request.getAgreementDateTo()).getDays() < 0)
                ? Optional.of(new ValidationError("agreementDates","Дата начала должна быть раньше даты окончания"))
                : Optional.empty();
    }
}
