package acalidonio.springbootjpa_template.common.validations;

import acalidonio.springbootjpa_template.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {
    private final ProductRepository productRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty())
            return true;

        return !productRepository.existsByNameIgnoreCase(value);
    }
}
