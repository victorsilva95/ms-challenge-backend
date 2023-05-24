package com.opahit.mschallengebackend.domain.enums;

import java.util.Arrays;

public enum TypeEnum {
    DEBIT,CREDIT;


    public static boolean checkStringIsEnum(String value) {
        return Arrays.stream(TypeEnum
                .values())
                .anyMatch(typeEnum -> typeEnum.toString().equals(value));
    }
}
