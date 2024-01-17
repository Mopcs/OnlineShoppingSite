package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByName(String name);

    Optional<Integer> findProductIdByUserId(int userId);

    @Query("SELECT p FROM Product p WHERE " +
            "(:size IS NULL OR p.size = :size) AND " +
            "(:category IS NULL OR p.category = :category) AND " +
            "(:name IS NULL OR p.name = :name) AND " +
            "(:productType IS NULL OR p.productType = :productType) AND " +
            "(:condition IS NULL OR p.condition = :condition) AND " +
            "(:color IS NULL OR p.color = :color)")
    List<Product> search(
            @Param("size") String size,
            @Param("category") String category,
            @Param("name") String name,
            @Param("productType") String productType,
            @Param("condition") String condition,
            @Param("color") String color
    );
}
