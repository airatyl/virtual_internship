package org.airat.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class RiskTypeRepositoryTest {
    @Autowired
    RiskTypeRepository riskTypeRepository;

    @Test
    public void isNull(){
        assertNull( riskTypeRepository.findByTitle("aasdad"));
    }

}