package acalidonio.springbootjpa_template.service;

import acalidonio.springbootjpa_template.domain.dto.request.CreateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.request.UpdateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.response.PageableResponse;
import acalidonio.springbootjpa_template.domain.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse getProductById(Long id);

    ProductResponse createProduct(CreateProductRequest request);

    PageableResponse<ProductResponse> getAllProducts(int page, int size, String category, boolean available);

    ProductResponse updateProduct(Long id, UpdateProductRequest request);

    ProductResponse deleteProduct(Long id);
}
