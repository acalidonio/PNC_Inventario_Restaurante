package acalidonio.springbootjpa_template.service.impl;

import acalidonio.springbootjpa_template.common.mappers.ProductMapper;
import acalidonio.springbootjpa_template.domain.dto.request.CreateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.request.UpdateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.response.PageableResponse;
import acalidonio.springbootjpa_template.domain.dto.response.ProductResponse;
import acalidonio.springbootjpa_template.domain.entities.Product;
import acalidonio.springbootjpa_template.exceptions.ResourceNotFoundException;
import acalidonio.springbootjpa_template.repository.ProductRepository;
import acalidonio.springbootjpa_template.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;


    @Override
    public ProductResponse getProductById(Long id) {
        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found"))
        );
    }

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        return mapper.toDto(
                repository.save(
                        mapper.toEntityCreate(request)));
    }

    @Override
    public PageableResponse<ProductResponse> getAllProducts(int page, int size, String category, boolean available) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = repository.findByCategoryAndAvailable(
                Product.Category.valueOf(category.toUpperCase()), available, pageable
        );

        Page<ProductResponse> responsePage = mapper.toDtoList(productPage);

        if (responsePage.isEmpty())
            throw new ResourceNotFoundException("No products were found");

        return PageableResponse.<ProductResponse>builder()
                .content(responsePage.getContent())
                .page(responsePage.getNumber())
                .size(responsePage.getSize())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .last(responsePage.isLast())
                .build();
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest request) {
        this.getProductById(id);
        return mapper.toDto(
                repository.save(
                        mapper.toEntityUpdate(request, id)
                )
        );
    }

    @Override
    @Transactional
    public ProductResponse deleteProduct(Long id) {
        ProductResponse exists = this.getProductById(id);
        repository.deleteById(id);
        return exists;
    }
}
