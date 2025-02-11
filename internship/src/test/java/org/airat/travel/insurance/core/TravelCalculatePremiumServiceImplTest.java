package org.airat.travel.insurance.core;

import org.airat.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    TravelPremiumUnderwriting underwriting;
    @Mock
    TravelCalculatePremiumRequestValidator requestValidator;

    private TravelCalculatePremiumRequest request;

    private TravelCalculatePremiumResponse response;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    public void initRequestAndResponse(){
        request= new TravelCalculatePremiumRequest("Айрат","Bilyaletdinov",
                LocalDate.of(2025, Month.JANUARY,20),LocalDate.of(2025, Month.JANUARY,29));

    }



    @Test
    public void returnResponseWithErrors(){
        Mockito.when(requestValidator.validate(request)).thenReturn(List.of(new ValidationError("field","message")));
        response=service.calculatePremium(request);
        assertEquals(response.getErrors().size(),1);
    }

    @Test
    public void returnResponseWithCorrectErrors(){
        Mockito.when(requestValidator.validate(request)).thenReturn(List.of(new ValidationError("field","message")));
        response=service.calculatePremium(request);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "message");
    }

    @Test
    public void responseFieldsNullIfHaveErrors(){
        Mockito.when(requestValidator.validate(request)).thenReturn(List.of(new ValidationError("field","message")));
        response=service.calculatePremium(request);
        assertNull(response.getPersonFirstName());
        assertNull(response.getPersonLastName());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPrice());
    }




    @Test
    public void responseFillingFirstName() {
        response= service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(),response.getPersonFirstName());

    }


    @Test
    public void responseFillingLastName() {
        response= service.calculatePremium(request);
        assertEquals(request.getPersonLastName(),response.getPersonLastName());

    }

    @Test
    public void responseFillingAgreementDateFrom() {
        response= service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(),response.getAgreementDateFrom());

    }

    @Test
    public void responseFillingAgreementDateTo() {
        response= service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(),response.getAgreementDateTo());

    }
    @Test
    public void responseCalculateAgreementPrice() {
        Mockito.when(underwriting.calculatePremium(Mockito.any())).thenReturn(new BigDecimal(8L));
        response= service.calculatePremium(request);
        assertEquals(new BigDecimal(8),response.getAgreementPrice());

    }



}