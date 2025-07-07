package org.airat.travel.insurance.core;

import org.airat.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.airat.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.airat.travel.insurance.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

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
                LocalDate.now().plusDays(1),LocalDate.now().plusDays(10),
                LocalDate.of(2005, Month.JANUARY,20),"Россия", BigDecimal.valueOf(100),
                List.of("Медицинские расходы","Отмена поездки"));

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
        assertNull(response.getAgreementPremium());
    }




    @Test
    public void responseFillingFirstName() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(request)).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(),response.getPersonFirstName());

    }


    @Test
    public void responseFillingLastName() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(request)).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(request.getPersonLastName(),response.getPersonLastName());

    }

    @Test
    public void responseFillingAgreementDateFrom() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(request)).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(),response.getAgreementDateFrom());

    }

    @Test
    public void responseFillingAgreementDateTo() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(request)).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(),response.getAgreementDateTo());

    }
    @Test
    public void responseCalculateAgreementPrice() {
        Mockito.when(underwriting.calculatePremiumForSelectedRisks(Mockito.any())).
                thenReturn(new TravelPremiumCalculationResult(new BigDecimal(8L),List.of(new RiskPremium())));
        response= service.calculatePremium(request);
        assertEquals(new BigDecimal(8),response.getAgreementPremium());

    }



}