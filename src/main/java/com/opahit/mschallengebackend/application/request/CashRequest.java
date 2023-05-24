package com.opahit.mschallengebackend.application.request;

import com.opahit.mschallengebackend.domain.enums.TypeEnum;
import com.opahit.mschallengebackend.infrastructure.configuration.annotations.ValidateDouble;
import com.opahit.mschallengebackend.infrastructure.configuration.annotations.ValidateLocalDateTime;
import com.opahit.mschallengebackend.infrastructure.configuration.annotations.ValidateTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

public class CashRequest {

    @Schema(example = "Empresa xyz", requiredMode = REQUIRED, description = "nome do fornecedor ou recebedor(Debito, Credito)")
    @NotEmpty(message = "clientName is required")
    private final String clientName;

    @Schema(example = "DEBIT", requiredMode = REQUIRED, description = "tipo de operação a ser realizada", anyOf = {TypeEnum.class})
    @ValidateTypeEnum
    private final String type;

    @Schema(example = "14.00", requiredMode = REQUIRED, description = "valor a ser creditado ou debitado")
    @ValidateDouble
    private final Double value;

    @Schema(example = "2022-01-01 00:00:00", requiredMode = NOT_REQUIRED, description = "data do registro")
    @ValidateLocalDateTime
    private final String date;

    public CashRequest(String clientName, String type, double value, String date) {
        this.clientName = clientName;
        this.type = type;
        this.value = value;
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public String getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}
