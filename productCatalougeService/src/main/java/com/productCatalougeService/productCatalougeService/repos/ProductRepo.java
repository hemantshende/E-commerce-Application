package com.productCatalougeService.productCatalougeService.repos;

import com.productCatalougeService.productCatalougeService.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Page<Product> findByNameEquals(String query, Pageable pageable);
}
