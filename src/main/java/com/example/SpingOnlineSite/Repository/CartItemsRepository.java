package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findAllByUserId(int userId);

    Optional<CartItem> findByUserIdAndProductId(int userId, int productId);
}
