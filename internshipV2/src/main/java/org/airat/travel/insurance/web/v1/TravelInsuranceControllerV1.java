package org.airat.travel.insurance.web.v1;


import org.airat.travel.insurance.core.services.RequestResponseConverter;
import org.airat.travel.insurance.core.services.TravelCalculatePremiumService;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class TravelInsuranceControllerV1 {

    private final TravelCalculatePremiumService service;

    TravelInsuranceControllerV1(TravelCalculatePremiumService service) {
        this.service = service;
    }

    @GetMapping("/insurance/travel/web/v1")
    public String showForm() {
        return "travel-calculate-premiumV1";
    }

    @PostMapping("/insurance/travel/web/v1")
    public @ResponseBody TravelCalculatePremiumResponseV1 processForm(@RequestBody TravelCalculatePremiumRequestV1 requestV1) {
        TravelCalculatePremiumRequestV2 requestV2= RequestResponseConverter.convertRequestV1ToRequestV2(requestV1);
        TravelCalculatePremiumResponseV2 responseV2 =service.calculatePremium(requestV2);
        return RequestResponseConverter.convertResponseV2ToResponseV1(responseV2);
    }

}
