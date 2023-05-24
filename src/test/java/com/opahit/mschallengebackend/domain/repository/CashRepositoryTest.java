package com.opahit.mschallengebackend.domain.repository;

import com.opahit.mschallengebackend.domain.enums.TypeEnum;
import com.opahit.mschallengebackend.domain.model.Cash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CashRepositoryTest {

    @Autowired
    private CashRepository cashRepository;

    @Test
    void givenMultiplesCash_whenRepositoryGetSumValueByDate_thenCalculateSumValue() {
        List<Cash> cashList = List.of(new Cash("teste", TypeEnum.DEBIT, -200.00, null),
                new Cash("teste", TypeEnum.DEBIT, -100.50, null),
                new Cash("teste", TypeEnum.CREDIT, 210.10, null),
                new Cash("teste", TypeEnum.CREDIT, 642.00, null));

        cashRepository.saveAll(cashList);
        Optional<Double> total = cashRepository.getSumValueByDate(LocalDate.now());

        Assertions.assertEquals(551.60, total.get());
    }
}
