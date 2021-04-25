package com.example.demo.utils.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DirectionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DirectionValid {
    String message() default "Поле может принимать значения только: ASC или DESC";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
