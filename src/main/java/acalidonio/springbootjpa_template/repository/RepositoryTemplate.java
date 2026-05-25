package acalidonio.springbootjpa_template.repository;

import acalidonio.springbootjpa_template.domain.entities.EntityTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RepositoryTemplate extends JpaRepository<EntityTemplate, UUID> {
}