package com.opahit.mschallengebackend.application.response;

public class CashConciliationResponse {

    private final Double value;

    public CashConciliationResponse(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
