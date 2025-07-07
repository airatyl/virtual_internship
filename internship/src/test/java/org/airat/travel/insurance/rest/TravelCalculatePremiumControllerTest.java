package org.airat.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.airat.travel.insurance.dto.RiskPremium;
import org.airat.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.airat.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.airat.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private TravelCalculatePremiumRequest rightRequest;

    private TravelCalculatePremiumResponse rightResponse;

    @BeforeEach
    public void initRequestAndResponse(){
        rightRequest= new TravelCalculatePremiumRequest("Айрат","Bilyaletdinov",
                LocalDate.now().plusDays(1),LocalDate.now().plusDays(10),
                LocalDate.of(2005, Month.JANUARY,20),"Россия", BigDecimal.valueOf(100),
                List.of("Медицинские расходы","Отмена поездки"));


    }

    @Test
    public void firstNameNotProvided() throws Exception {

        rightRequest.setPersonFirstName(null);
        rightResponse =new TravelCalculatePremiumResponse(List.of(new ValidationError("personFirstName", "Должно быть заполнено")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void lastNameNotProvidedTest() throws Exception {

        rightRequest.setPersonLastName(null);
        rightResponse =new TravelCalculatePremiumResponse(List.of(new ValidationError("personLastName", "Должно быть заполнено")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void agreementDateFromNotProvidedTest() throws Exception {

        rightRequest.setAgreementDateFrom(null);
        rightResponse =new TravelCalculatePremiumResponse(List.of(new ValidationError("agreementDateFrom", "Должно быть заполнено")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void agreementDateToNotProvidedTest() throws Exception {

        rightRequest.setAgreementDateTo(null);
        rightResponse =new TravelCalculatePremiumResponse(List.of(new ValidationError("agreementDateTo", "Должно быть заполнено")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void agreementDateFromNotInFutureTest() throws Exception {

        rightRequest.setAgreementDateFrom(LocalDate.of(2024,1,1));
        rightResponse =new TravelCalculatePremiumResponse(List.of(new ValidationError("agreementDateFrom", "Дата начала должна быть в будущем")));

        executeAndCompare(rightRequest,rightResponse);
    }
    @Test
    public void agreementDateToNotInFutureTest() throws Exception {
        rightRequest.setAgreementDateTo(LocalDate.of(2024,1,1));
        rightResponse =new TravelCalculatePremiumResponse(List.of(new ValidationError("agreementDates","Дата начала должна быть раньше даты окончания"),new ValidationError("agreementDateTo", "Дата окончания должна быть в будущем")));

        executeAndCompare(rightRequest,rightResponse);
    }

    @Test
    public void agreementDateFromBeforeAgreementDateToTest() throws Exception {

        rightRequest.setAgreementDateFrom(LocalDate.of(2025,9,13));
        rightRequest.setAgreementDateTo(LocalDate.of(2025,9,10));
        rightResponse =new TravelCalculatePremiumResponse(List.of(new ValidationError("agreementDates","Дата начала должна быть раньше даты окончания")));

        executeAndCompare(rightRequest,rightResponse);
    }



//    @Test
//    public void allFieldsNotProvidedTest() throws Exception {
//
//
//    }


    @Test
    public void AllFieldsCorrectTest() throws Exception {
        rightResponse = new TravelCalculatePremiumResponse("Айрат","Bilyaletdinov",
                LocalDate.now().plusDays(1),LocalDate.now().plusDays(10),
                LocalDate.of(2005, Month.JANUARY,20),"Россия", BigDecimal.valueOf(100),BigDecimal.valueOf(6001),
                List.of(new RiskPremium("Медицинские расходы",BigDecimal.valueOf(6000)),new RiskPremium("Отмена поездки",BigDecimal.ONE)));

        executeAndCompare(rightRequest,rightResponse);
    }

    private void executeAndCompare(TravelCalculatePremiumRequest request,
                                   TravelCalculatePremiumResponse expectedResponse) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(mapper.writeValueAsString(request))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(expectedResponse,mapper.readValue(response,TravelCalculatePremiumResponse.class));
    }

}
