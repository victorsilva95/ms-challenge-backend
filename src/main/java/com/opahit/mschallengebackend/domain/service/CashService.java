package com.opahit.mschallengebackend.domain.service;

import com.opahit.mschallengebackend.application.response.CashConciliationResponse;
import com.opahit.mschallengebackend.domain.dto.CashDTO;

public interface CashService {
    void createCash(CashDTO cashDTO);
    CashConciliationResponse getCashConciliation(String date);
}
