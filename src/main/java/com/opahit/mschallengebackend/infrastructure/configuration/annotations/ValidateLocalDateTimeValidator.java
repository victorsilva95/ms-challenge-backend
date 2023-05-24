package com.opahit.mschallengebackend.infrastructure.configuration.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.opahit.mschallengebackend.infrastructure.shared.StringConstants.PATTERN_DATE_TIME;

public class ValidateLocalDateTimeValidator implements ConstraintValidator<ValidateLocalDateTime, String> {

    @Override
    public void initialize(ValidateLocalDateTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (s != null && !s.isEmpty()) {
            try {
                LocalDateTime.parse(s, DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
            } catch (DateTimeParseException de) {
                constraintValidatorContext.buildConstraintViolationWithTemplate(String.format("date %s is not valid date, format : %s",
                        s, PATTERN_DATE_TIME)).addConstraintViolation();
                return false;
            }
        }
        return true;

    }
}
