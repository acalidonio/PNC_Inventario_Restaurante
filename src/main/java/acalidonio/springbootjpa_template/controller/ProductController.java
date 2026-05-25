package acalidonio.springbootjpa_template.controller;

import acalidonio.springbootjpa_template.domain.dto.request.CreateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.request.UpdateProductRequest;
import acalidonio.springbootjpa_template.domain.dto.response.GeneralResponse;
import acalidonio.springbootjpa_template.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl service;

    @GetMapping("/getProducts")
    public ResponseEntity<GeneralResponse> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "FOOD") String category,
            @RequestParam(defaultValue = "true") boolean available
    ) {
        return buildResponse(
                "All products retrieved successfully",
                HttpStatus.OK,
                service.getAllProducts(page, size, category, available)
        );
    }

    @GetMapping("/getProducts/{id}")
    public ResponseEntity<GeneralResponse> getProductsById(
            @PathVariable Long id
    ) {
        return buildResponse(
                "Product Found",
                HttpStatus.OK,
                service.getProductById(id)
        );
    }

    @PostMapping("/createProduct")
    public ResponseEntity<GeneralResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        return buildResponse(
          "Product created successfully",
          HttpStatus.CREATED,
          service.createProduct(request)
        );
    }


    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<GeneralResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request
            ) {
        return buildResponse(
                "Product details updated successfully",
                HttpStatus.OK,
                service.updateProduct(id, request));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<GeneralResponse> deleteProduct(
            @PathVariable Long id
    ) {
        return buildResponse(
                "Specimen purged from Sheikah Slate",
                HttpStatus.OK,
                service.deleteProduct(id)
        );
    }


    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status).body(
                GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
        );
    }
}
