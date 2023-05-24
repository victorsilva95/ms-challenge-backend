package com.opahit.mschallengebackend.application.request.mapper;

import com.opahit.mschallengebackend.application.request.CashRequest;
import com.opahit.mschallengebackend.domain.dto.CashDTO;
import com.opahit.mschallengebackend.domain.enums.TypeEnum;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.opahit.mschallengebackend.domain.enums.TypeEnum.*;
import static com.opahit.mschallengebackend.infrastructure.shared.StringConstants.PATTERN_DATE_TIME;

public class CashRequestMapper {

    private CashRequestMapper() {
        throw new IllegalStateException("Utility class");
    }
    private static final DecimalFormat df = new DecimalFormat("#.##");
    public static CashDTO convertIntoCashDTO(CashRequest cashRequest) {
        TypeEnum type = valueOf(cashRequest.getType());
        Double value = formatValueByType(type, cashRequest.getValue());
        return new CashDTO(cashRequest.getClientName(), type, value, convertStringToDate(cashRequest.getDate()));
    }

    private static Double formatValueByType(TypeEnum typeEnum, Double value) {
        Double formattedValue = typeEnum == DEBIT ? (Math.abs(value) * -1) : Math.abs(value);
        return Double.valueOf(df.format(formattedValue));
    }

    private static LocalDateTime convertStringToDate(String date) {
        return date == null ? null : LocalDateTime.parse(date, DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
    }
}
