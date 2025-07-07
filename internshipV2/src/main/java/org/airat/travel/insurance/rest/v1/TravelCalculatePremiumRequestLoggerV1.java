package org.airat.travel.insurance.rest.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestLoggerV1 {

    private final static Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLoggerV1.class);

    void log (TravelCalculatePremiumRequestV1 request){
        ObjectMapper objectMapper =new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String result = objectMapper.writeValueAsString(request);
            logger.info("Request: {}",result);
        } catch (JsonProcessingException e) {
            logger.error("Ошибка конвертировать запрос в JSON",e);
        }
    }
}
