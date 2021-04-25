package com.example.demo.utils.validation;

import org.springframework.data.domain.Sort;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DirectionValidator implements ConstraintValidator<DirectionValid, String> {
    @Override
    public void initialize(DirectionValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.toUpperCase().contains(Sort.Direction.ASC.name().toUpperCase())
                || s.toUpperCase().contains(Sort.Direction.DESC.name().toUpperCase());
    }
}