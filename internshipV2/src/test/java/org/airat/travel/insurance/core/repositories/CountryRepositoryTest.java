package org.airat.travel.insurance.core.repositories;

import org.airat.travel.insurance.core.domain.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryRepositoryTest {
    @Autowired
    CountryRepository countryRepository;

    @Test
    public void test1(){
        List<Country> countries= countryRepository.findAll();
        System.out.println(countries);
        assertNotNull(countries);
    }

}