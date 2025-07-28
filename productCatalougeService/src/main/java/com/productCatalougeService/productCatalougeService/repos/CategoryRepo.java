package com.productCatalougeService.productCatalougeService.repos;

//import org.example.productcatalogservice_june2025.models.Category;
import com.productCatalougeService.productCatalougeService.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    Optional<Category> findById(Long id);
}
