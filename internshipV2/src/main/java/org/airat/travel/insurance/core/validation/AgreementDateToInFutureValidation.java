package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AgreementDateToInFutureValidation implements RequestFieldValidation{

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        return (request.getAgreementDateTo()!=null &&
                LocalDate.now().until(request.getAgreementDateTo()).getDays() < 0)
                ? Optional.of(new ValidationError("agreementDateTo","Дата окончания должна быть в будущем"))
                : Optional.empty();
    }
}
