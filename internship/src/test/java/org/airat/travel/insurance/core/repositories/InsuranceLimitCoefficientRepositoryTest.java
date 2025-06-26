package org.airat.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class InsuranceLimitCoefficientRepositoryTest {

    @Autowired
    private InsuranceLimitCoefficientRepository insuranceLimitCoefficientRepository;

    @Test
    public void injectedRepositoryAreNotNull(){
        assertNotNull(insuranceLimitCoefficientRepository);
    }

    @Test
    public void findCoefByLimitCorrectly(){
        assertEquals(1.0,insuranceLimitCoefficientRepository.
                findInsuranceLimitCoefficientByLimit(12000).getCoefficient());
    }
}