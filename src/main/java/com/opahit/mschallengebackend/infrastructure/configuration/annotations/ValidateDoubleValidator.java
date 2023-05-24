package com.opahit.mschallengebackend.infrastructure.configuration.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateDoubleValidator implements ConstraintValidator<ValidateDouble, Double> {

    @Override
    public void initialize(ValidateDouble constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        return value > 0;
    }
}
