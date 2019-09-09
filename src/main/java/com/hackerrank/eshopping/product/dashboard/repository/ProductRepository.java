package com.hackerrank.eshopping.product.dashboard.repository;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface User repository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product u WHERE u.category = ?1")
    List<Product> findByCategory(String category);

    @Query("FROM Product u WHERE u.category = ?1 and u.availability = ?2")
    List<Product> findProductsByCategoryAndAvailability(String category, boolean availability);
}
