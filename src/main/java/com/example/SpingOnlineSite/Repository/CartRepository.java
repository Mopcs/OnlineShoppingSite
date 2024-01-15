package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByCartId(int cartId);

    Optional<Cart> findByUserId(int userId);
}