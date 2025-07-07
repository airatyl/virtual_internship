package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class AgreementDateToNotEmptyValidation implements RequestFieldValidation{
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Должно быть заполнено"))
                : Optional.empty();
    }
}
