package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateFromNotEmptyValidationTest {


    AgreementDateFromNotEmptyValidation agreementDateFromNotEmptyValidation=new AgreementDateFromNotEmptyValidation();

    @Test
    public void agreementDateFromIsNullValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> errors = agreementDateFromNotEmptyValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("agreementDateFrom", "Должно быть заполнено"),errors.get());
    }

    @Test
    public void agreementDateFromIsNotEmptyValidate(){
        TravelCalculatePremiumRequestV2 request= Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025,1,12));
        Optional <ValidationError> errors = agreementDateFromNotEmptyValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

}