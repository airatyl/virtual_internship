package org.airat.travel.insurance.core.repositories;

import org.airat.travel.insurance.core.domain.AgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgeCoefficientRepository extends JpaRepository<AgeCoefficient, Integer> {

    @Query("select a from AgeCoefficient a where a.fromage <= ?1 and a.toage>= ?1")
    AgeCoefficient findAgeCoefficientByAge(int age);

}
