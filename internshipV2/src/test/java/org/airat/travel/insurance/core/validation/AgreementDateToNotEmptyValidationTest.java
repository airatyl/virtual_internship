package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateToNotEmptyValidationTest {

    AgreementDateToNotEmptyValidation agreementDateToNotEmptyValidation=new AgreementDateToNotEmptyValidation();

    @Test
    public void agreementDateToIsNullValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> errors = agreementDateToNotEmptyValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("agreementDateTo", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void agreementDateToIsNotEmptyValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025,1,12));
        Optional <ValidationError> errors = agreementDateToNotEmptyValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

}