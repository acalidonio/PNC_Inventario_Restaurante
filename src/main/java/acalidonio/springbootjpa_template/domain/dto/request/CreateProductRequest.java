package acalidonio.springbootjpa_template.domain.dto.request;

import acalidonio.springbootjpa_template.common.validations.UniqueProductName;
import acalidonio.springbootjpa_template.domain.entities.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotNull(message = "El nombre es requerido")
    @UniqueProductName
    private String name;

    private String description;

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @NotNull(message = "La cantidad es requerida")
    @PositiveOrZero(message = "La cantidad debe ser mayor o igual a 0")
    private Integer quantity;

    @NotNull(message = "La categoría es requerida")
    private Product.Category category;
}