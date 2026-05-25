package acalidonio.springbootjpa_template.common.mappers;

import acalidonio.springbootjpa_template.domain.dto.request.RequestTemplate;
import acalidonio.springbootjpa_template.domain.dto.response.TemplateResponse;
import acalidonio.springbootjpa_template.domain.entities.EntityTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MapperTemplate {
    public EntityTemplate toEntityUpdate(RequestTemplate request, UUID id) {
        return EntityTemplate.builder()
                .id(id)
                .name(request.getName())
                .age(request.getAge())
                .bool(request.getBool())
                .build();
    }

    public TemplateResponse toDto(EntityTemplate entity) {
        return TemplateResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .bool(entity.getBool())
                .build();
    }

    public Page<TemplateResponse> toDtoList(Page<EntityTemplate> entities) {
        return entities.map(this::toDto);
    }
}
