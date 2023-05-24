package com.opahit.mschallengebackend.domain.service.impl;

import com.opahit.mschallengebackend.application.response.CashConciliationResponse;
import com.opahit.mschallengebackend.domain.dto.CashDTO;
import com.opahit.mschallengebackend.domain.model.Cash;
import com.opahit.mschallengebackend.domain.repository.CashRepository;
import com.opahit.mschallengebackend.domain.service.CashService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.opahit.mschallengebackend.infrastructure.shared.StringConstants.PATTERN_DATE;

@Service
public class CashServiceImpl implements CashService {

    private final CashRepository cashRepository;

    public CashServiceImpl(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public void createCash(CashDTO cashDTO) {
        Cash cash = new Cash(cashDTO);
        cashRepository.save(cash);
    }

    @Override
    public CashConciliationResponse getCashConciliation(String date) {
        LocalDate result = date == null ? LocalDate.now() : LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN_DATE));
        Optional<Double> total = cashRepository.getSumValueByDate(result);

        return new CashConciliationResponse(total.orElse(0.00));
    }
}
