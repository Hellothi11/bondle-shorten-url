package com.bondle.shortenurl.dto.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author nguyen.tam.thi at 12:55 AM 4/5/21
 */
@Documented
@Constraint(validatedBy = URLValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface URLConstraint {

  String message() default "Invalid URL";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
