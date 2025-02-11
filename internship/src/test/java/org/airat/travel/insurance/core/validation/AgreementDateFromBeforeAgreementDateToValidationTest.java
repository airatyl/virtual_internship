package org.airat.travel.insurance.core.validation;

import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateFromBeforeAgreementDateToValidationTest {

    AgreementDateFromBeforeAgreementDateToValidation agreementDateFromBeforeAgreementDateToValidation= new AgreementDateFromBeforeAgreementDateToValidation();

    @Test
    public void agreementDateFromBeforeAgreementDateToValidation(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025,1,10));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025,1,12));
        Optional<ValidationError> errors = agreementDateFromBeforeAgreementDateToValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void agreementDateFromEqualsAgreementDateToValidation(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025,1,12));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025,1,12));
        Optional<ValidationError> errors = agreementDateFromBeforeAgreementDateToValidation.validateField(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void agreementDateFromAfterAgreementDateToValidation(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025,1,12));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025,1,10));
        Optional<ValidationError> errors = agreementDateFromBeforeAgreementDateToValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("agreementDates","agreementDateFrom should be no earlier than agreementDateTo"),errors.get());
    }

    @Test
    public void agreementDateFromIsNullAgreementDateToIsPresentValidation(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025,1,12));
        Optional<ValidationError> errors = agreementDateFromBeforeAgreementDateToValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("agreementDates","agreementDateFrom should be no earlier than agreementDateTo"),errors.get());
    }

    @Test
    public void agreementDateFromIsPresentAgreementDateToIsNullValidation(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025,1,12));
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> errors = agreementDateFromBeforeAgreementDateToValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("agreementDates","agreementDateFrom should be no earlier than agreementDateTo"),errors.get());
    }

    @Test
    public void agreementDateFromIsNullAgreementDateToIsNullValidation(){
        TravelCalculatePremiumRequest request= Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> errors = agreementDateFromBeforeAgreementDateToValidation.validateField(request);
        assertTrue(errors.isPresent());
        assertEquals(new ValidationError("agreementDates","agreementDateFrom should be no earlier than agreementDateTo"),errors.get());
    }



}