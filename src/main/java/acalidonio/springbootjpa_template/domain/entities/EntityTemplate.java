package acalidonio.springbootjpa_template.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "EntityTemplate")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Serial generation type (1, 2, 3, etc...)
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "bool")
    private Boolean bool;
}
