package com.opahit.mschallengebackend.infrastructure.configuration.annotations;

import com.opahit.mschallengebackend.domain.enums.TypeEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ValidateTypeEnumValidator implements ConstraintValidator<ValidateTypeEnum, String> {
    @Override
    public void initialize(ValidateTypeEnum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if(s == null || s.isBlank()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("type is required").addConstraintViolation();
            return false;
        }
        else if(!TypeEnum.checkStringIsEnum(s)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.format("type %s not exist, possible values: %s",
                    s,
                    Arrays.toString(TypeEnum.values()))).addConstraintViolation();
            return false;
        }
        return true;
    }
}
