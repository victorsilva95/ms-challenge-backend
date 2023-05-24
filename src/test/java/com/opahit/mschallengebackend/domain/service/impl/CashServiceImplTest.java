package com.opahit.mschallengebackend.domain.service.impl;

import com.opahit.mschallengebackend.application.response.CashConciliationResponse;
import com.opahit.mschallengebackend.domain.dto.CashDTO;
import com.opahit.mschallengebackend.domain.enums.TypeEnum;
import com.opahit.mschallengebackend.domain.model.Cash;
import com.opahit.mschallengebackend.domain.repository.CashRepository;
import com.opahit.mschallengebackend.domain.service.CashService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CashServiceImplTest {

    @Mock
    private CashRepository cashRepository;


    @Test
    void givenCashDTO_whenServiceCreateCash_thenSuccess() {
        CashDTO cashDTO = new CashDTO("teste", TypeEnum.DEBIT, 10.00, null);

        CashService cashService = new CashServiceImpl(cashRepository);
        cashService.createCash(cashDTO);

        Mockito.verify(cashRepository).save(Mockito.any(Cash.class));

    }

    @Test
    void givenDateNull_whenServiceGetCashConciliation_thenReturnValue() {
        Mockito.when(cashRepository.getSumValueByDate(LocalDate.now())).thenReturn(Optional.of(15.00));

        CashService cashService = new CashServiceImpl(cashRepository);
        CashConciliationResponse cashConciliation = cashService.getCashConciliation(null);

        Assertions.assertNotNull(cashConciliation);
        Assertions.assertEquals(15.00, cashConciliation.getValue());

    }

    @Test
    void givenDate_whenServiceGetCashConciliation_thenReturnEmptyValue() {
        Mockito.when(cashRepository.getSumValueByDate(LocalDate.of(2022, 1, 1))).thenReturn(Optional.empty());

        CashService cashService = new CashServiceImpl(cashRepository);
        CashConciliationResponse cashConciliation = cashService.getCashConciliation("2022-01-01");

        Assertions.assertNotNull(cashConciliation);
        Assertions.assertEquals(0.00, cashConciliation.getValue());

    }
}
