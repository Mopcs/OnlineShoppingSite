package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findAllByCartId(int cartId);
}
