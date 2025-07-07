package org.airat.travel.insurance.core.repositories;

import org.airat.travel.insurance.core.domain.InsuranceLimitCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface InsuranceLimitCoefficientRepository extends JpaRepository<InsuranceLimitCoefficient,Integer> {

    @Query("select i from InsuranceLimitCoefficient i where i.insurancelimitfrom<= ?1 and i.insurancelimitto>= ?1")
    InsuranceLimitCoefficient findInsuranceLimitCoefficientByLimit(BigDecimal limit);
}
