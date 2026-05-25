package acalidonio.springbootjpa_template.common.mappers;

import acalidonio.springbootjpa_template.domain.dto.request.CreateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.request.UpdateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.response.ProductResponse;
import acalidonio.springbootjpa_template.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntityCreate(CreateProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .build();
    }

    public Product toEntityUpdate(UpdateProductRequest request, Long id) {
        return Product.builder()
                .id(id)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .available(request.getAvailable())
                .category(request.getCategory())
                .build();
    }

    public ProductResponse toDto(Product entity) {
        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .available(entity.getAvailable())
                .category(entity.getCategory())
                .build();
    }

    public Page<ProductResponse> toDtoList(Page<Product> entities) {
        return entities.map(this::toDto);
    }
}
