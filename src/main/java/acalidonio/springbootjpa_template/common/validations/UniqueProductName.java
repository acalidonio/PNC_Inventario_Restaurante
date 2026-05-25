package acalidonio.springbootjpa_template.common.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueProductNameValidator.class)
@Documented
public @interface UniqueProductName {
    String message() default "Product name must be unique.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}