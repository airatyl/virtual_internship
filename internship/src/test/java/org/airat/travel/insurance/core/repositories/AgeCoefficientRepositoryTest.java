package org.airat.travel.insurance.core.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AgeCoefficientRepositoryTest {


    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;


    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(ageCoefficientRepository);
    }

    @Test
    public void findAgeCoefCorrectly(){
        double coef = ageCoefficientRepository.findAgeCoefficientByAge(15).getCoefficient();
        assertEquals(0.8,coef);
    }

}