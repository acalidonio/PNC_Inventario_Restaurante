package acalidonio.springbootjpa_template.repository;

import acalidonio.springbootjpa_template.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameIgnoreCase(String name);

    Page<Product> findByCategoryAndAvailable(Product.Category category, Boolean available, Pageable pageable);
}