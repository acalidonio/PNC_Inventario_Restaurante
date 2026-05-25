package acalidonio.springbootjpa_template.domain.dto.response;

import acalidonio.springbootjpa_template.domain.entities.Product;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Boolean available;
    private Product.Category category;
}
