package org.airat.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader reader;


    @Test
    public void firstNameNotProvidedTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_firstName_not_provided.json",
                "rest/TravelCalculatePremium/Response_firstName_not_provided.json");
    }
    @Test
    public void lastNameNotProvidedTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_lastName_not_provided.json",
                "rest/TravelCalculatePremium/Response_lastName_not_provided.json");
    }
    @Test
    public void agreementDateFromNotProvidedTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremium/Response_agreementDateFrom_not_provided.json");
    }
    @Test
    public void agreementDateFromNotInFutureTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_agreementDateFrom_not_in_future.json",
                "rest/TravelCalculatePremium/Response_agreementDateFrom_not_in_future.json");
    }
    @Test
    public void agreementDateToNotInFutureTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_agreementDateTo_not_in_future.json",
                "rest/TravelCalculatePremium/Response_agreementDateTo_not_in_future.json");
    }
    @Test
    public void agreementDateToNotProvidedTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremium/Response_agreementDateTo_not_provided.json");
    }
    @Test
    public void agreementDateFromBeforeAgreementDateToTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_dateTo_lessThen_dateFrom.json",
                "rest/TravelCalculatePremium/Response_dateTo_lessThen_dateFrom.json");
    }

    @Test
    public void allFieldsNotProvidedTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_allFields_not_provided.json",
                "rest/TravelCalculatePremium/Response_allFields_not_provided.json");
    }


    @Test
    public void AllFieldsCorrectTest() throws Exception {

        executeAndCompare("rest/TravelCalculatePremium/Request_firstName_not_provided.json",
                "rest/TravelCalculatePremium/Response_firstName_not_provided.json");
    }

    private void executeAndCompare(String jsonRequestFilePath,
                                   String jsonResponseFilePath) throws Exception {
        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(reader.readJsonFromFile(jsonRequestFilePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(reader.readJsonFromFile(jsonResponseFilePath)), mapper.readTree(response));
    }

}
