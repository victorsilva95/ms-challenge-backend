package com.opahit.mschallengebackend.infrastructure.configuration.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.opahit.mschallengebackend.infrastructure.shared.StringConstants.PATTERN_DATE;

public class ValidateLocalDateValidator implements ConstraintValidator<ValidateLocalDate, String> {

    @Override
    public void initialize(ValidateLocalDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (s != null && !s.isEmpty()) {
            try {
                LocalDate.parse(s, DateTimeFormatter.ofPattern(PATTERN_DATE));
            } catch (DateTimeParseException de) {
                constraintValidatorContext.buildConstraintViolationWithTemplate(String.format("date %s is not valid date, format : %s",
                        s, PATTERN_DATE)).addConstraintViolation();
                return false;
            }
        }
        return true;

    }
}
