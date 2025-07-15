package org.airat.travel.insurance.core.repositories;

import org.airat.travel.insurance.core.domain.AgreementPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementPersonRepository extends JpaRepository<AgreementPerson,Integer> {
}
