package org.airat.travel.insurance.core.repositories;

import org.airat.travel.insurance.core.domain.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement,Integer> {
}
