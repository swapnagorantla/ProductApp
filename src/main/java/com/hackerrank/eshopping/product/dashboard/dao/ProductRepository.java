package com.hackerrank.eshopping.product.dashboard.dao;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findProductsByCategory(String category);
    List<Product> findProductsByCategoryAndAvailability(String category, boolean avail);
    Product findProductsById(Long id);
}
