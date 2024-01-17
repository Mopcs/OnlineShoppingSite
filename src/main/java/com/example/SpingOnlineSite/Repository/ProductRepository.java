package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByName(String name);

    List<Product> findByGender(String gender);
    Optional<Integer> findProductIdByUserId(int userId);
}
