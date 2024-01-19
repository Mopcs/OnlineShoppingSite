package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName(String name);

    Optional<Integer> findProductIdByUserId(int userId);

    @Query("SELECT p FROM Product p WHERE " +
            "(:size IS NULL OR p.size = :size) AND " +
            "(:category IS NULL OR p.category = :category) AND " +
            "(:productType IS NULL OR p.productType = :productType) AND " +
            "(:condition IS NULL OR p.condition = :condition) AND " +
            "(:chapter IS NULL OR p.chapter = :chapter) AND" +
            "(:color IS NULL OR p.color = :color) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND "+
            "p.productId IN :productIds")
    List<Product> search(
            @Param("size") String size,
            @Param("category") String category,
            @Param("productType") String productType,
            @Param("condition") String condition,
            @Param("chapter") String chapter,
            @Param("color") String color,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("productIds") List<Integer> productIds
    );

    @Query("SELECT DISTINCT p.name FROM Product p")
    List<String> findAllProductNames();

    @Query("SELECT p.productId FROM Product p WHERE p.name = :productName")
    Integer getIdByName(String productName);
}
