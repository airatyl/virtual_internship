package org.airat.travel.insurance.rest.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Component
public class TravelCalculatePremiumRequestExecutionTimeLoggerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLoggerV1.class);

    void log(StopWatch watch) {
        watch.stop();
        logger.info("Время обработки запроса = {} мс", watch.getTotalTimeMillis());
    }
}
