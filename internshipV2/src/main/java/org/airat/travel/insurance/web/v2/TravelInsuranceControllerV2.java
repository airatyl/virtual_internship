package org.airat.travel.insurance.web.v2;


import org.airat.travel.insurance.core.services.RequestResponseConverter;
import org.airat.travel.insurance.core.services.TravelCalculatePremiumService;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.airat.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.airat.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TravelInsuranceControllerV2 {

    private final TravelCalculatePremiumService service;

    TravelInsuranceControllerV2(TravelCalculatePremiumService service) {
        this.service = service;
    }

    @GetMapping("/insurance/travel/web/v2")
    public String showForm() {
        return "travel-calculate-premiumV2";
    }

    @PostMapping("/insurance/travel/web/v2")
    public @ResponseBody TravelCalculatePremiumResponseV2 processForm(@RequestBody TravelCalculatePremiumRequestV2 requestV2) {
        return service.calculatePremium(requestV2);
    }

}
