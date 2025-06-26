package org.airat.travel.insurance.core.repositories;

import org.airat.travel.insurance.core.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    Country findByTitle(String title);
}
