package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface RequestFieldValidation {
    Optional <ValidationError> validateField(TravelCalculatePremiumRequestV2 request);

}
