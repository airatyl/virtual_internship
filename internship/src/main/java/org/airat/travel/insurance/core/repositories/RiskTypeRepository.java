package org.airat.travel.insurance.core.repositories;

import org.airat.travel.insurance.core.domain.RiskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiskTypeRepository extends JpaRepository<RiskType,Integer> {

    RiskType findByTitle(String title);
}
