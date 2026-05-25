package acalidonio.springbootjpa_template.domain.dto.request;

import acalidonio.springbootjpa_template.domain.entities.Product;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    @NotNull(message = "El id es requerido")
    private Long id;
    
    private String name;
    private String description;

    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @PositiveOrZero(message = "La cantidad debe ser mayor o igual a 0")
    private Integer quantity;

    @AssertTrue(message = "El campo available debe ser true siempre al editarse manualmente")
    private Boolean available;

    private Product.Category category;
}
