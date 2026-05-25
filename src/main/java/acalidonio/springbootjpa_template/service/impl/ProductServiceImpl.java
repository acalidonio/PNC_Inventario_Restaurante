package acalidonio.springbootjpa_template.service.impl;

import acalidonio.springbootjpa_template.common.mappers.ProductMapper;
import acalidonio.springbootjpa_template.domain.dto.request.CreateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.request.UpdateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.response.PageableResponse;
import acalidonio.springbootjpa_template.domain.dto.response.ProductResponse;
import acalidonio.springbootjpa_template.domain.entities.Product;
import acalidonio.springbootjpa_template.exceptions.BusinessRuleException;
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
        Product product = mapper.toEntityCreate(request);

        product.setAvailable(product.getQuantity() != 0);

        return mapper.toDto(repository.save(product));
    }

    @Override
    public PageableResponse<ProductResponse> getAllProducts(int page, int size, String category, boolean available) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;
        if (category == null) {
            productPage = repository.findAll(pageable);
        } else {
            productPage = repository.findByCategoryAndAvailable(
                    Product.Category.valueOf(category.toUpperCase()), available, pageable
            );
        }

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
        ProductResponse product = this.getProductById(id);

        int newStock = request.getQuantity();

        if (newStock < 0) {
            throw new BusinessRuleException("El stock resultante no puede ser negativo");
        }

        product.setQuantity(newStock);

        product.setAvailable(product.getQuantity() != 0);

        return mapper.toDto(
                repository.save(mapper.toEntityUpdate(request, id))
        );
    }

    @Override
    @Transactional
    public ProductResponse deleteProduct(Long id) {
        ProductResponse product = this.getProductById(id);

        if (product.getCategory() == Product.Category.INGREDIENT && product.getAvailable()) {
            throw new BusinessRuleException("No se puede eliminar el producto porque aún tiene existencias en inventario.");
        }
        
        repository.deleteById(id);
        return product;
    }
}
