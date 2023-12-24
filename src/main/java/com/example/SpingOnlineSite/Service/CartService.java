package com.example.SpingOnlineSite.Service;

import com.example.SpingOnlineSite.Entity.Cart;
import com.example.SpingOnlineSite.Entity.CartItem;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Entity.User;
import com.example.SpingOnlineSite.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllItemsInCart() {
        return cartRepository.findAll();
    }

    public void addItemToCart(int userId, int productId, int quantity) {
        Cart cartItem = new Cart();
        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        cartRepository.save(cartItem);
    }

    public Optional<Cart> getCartByUserId(int id)
    {
       return cartRepository.findById(id);
    }

    public BigDecimal calculateTotal(Optional<Cart> cart) {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart.get().getItems()) {
            BigDecimal itemTotal = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(itemTotal);
        }

        return total;
    }

    public void removeFromCart(int cartId) {
        cartRepository.deleteById(cartId);
    }
}