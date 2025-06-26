package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

interface RequestFieldValidation {
    Optional <ValidationError> validateField(TravelCalculatePremiumRequest request);

}
