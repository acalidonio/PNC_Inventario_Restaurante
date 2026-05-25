package acalidonio.springbootjpa_template.domain.dto.response;

import lombok.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResponse {
    private UUID id;
    private String name;
    private Integer age;
    private Boolean bool;
}
