package acalidonio.springbootjpa_template.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestTemplate {
    @NotBlank(message = "The name cannot be empty.")
    private String name;

    @NotNull(message = "Age is required.")
    private Integer age;

    @NotNull(message = "You must specify true or false.")
    private Boolean bool;
}