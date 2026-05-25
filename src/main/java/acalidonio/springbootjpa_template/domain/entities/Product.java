package acalidonio.springbootjpa_template.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "available")
    private Boolean available;

    public enum Category {
        DRINK, FOOD, DESSERT, INGREDIENT
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;
}
