package org.airat.travel.insurance.rest.v1;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.airat.travel.insurance.core.services.RequestResponseConverter;
import org.airat.travel.insurance.core.services.TravelCalculatePremiumService;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@RestController
@RequestMapping("/insurance/travel/rest/v1")
public class TravelCalculatePremiumControllerV1 {

	private final TravelCalculatePremiumRequestExecutionTimeLoggerV1 timeLogger;
	private final TravelCalculatePremiumRequestLoggerV1 requestLogger;
	private final TravelCalculatePremiumResponseLoggerV1 responseLogger;

	private final TravelCalculatePremiumService calculatePremiumService;


	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 requestV1) {
		StopWatch watch =new StopWatch();
		watch.start();
		requestLogger.log(requestV1);
		TravelCalculatePremiumRequestV2 requestV2= RequestResponseConverter.convertRequestV1ToRequestV2(requestV1);
		TravelCalculatePremiumResponseV2 responseV2 =calculatePremiumService.calculatePremium(requestV2);
		TravelCalculatePremiumResponseV1 responseV1 = RequestResponseConverter.convertResponseV2ToResponseV1(responseV2);
		timeLogger.log(watch);
		responseLogger.log(responseV1);
		return responseV1;
	}


}