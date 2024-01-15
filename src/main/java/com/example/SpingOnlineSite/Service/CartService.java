package com.example.SpingOnlineSite.Service;

import com.example.SpingOnlineSite.Entity.Cart;
import com.example.SpingOnlineSite.Entity.CartItem;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Repository.CartItemsRepository;
import com.example.SpingOnlineSite.Repository.CartRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemsRepository cartItemsRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService, CartItemsRepository cartItemsRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemsRepository = cartItemsRepository;
    }

    public List<Cart> getAllItemsInCart() {
        return cartRepository.findAll();
    }

    public CartItem addItemToCart(int cartId, int productId) {
        Cart cart = cartRepository.findCartByCartId(cartId);

        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new ResourceNotFoundException("Продукт не найден по id: " + productId);
        }

        CartItem cartItem = new CartItem();
        cartItem.setCartId(cart.getCartId());
        cartItem.setProductId(product.getProductId());

        return cartItemsRepository.save(cartItem);
    }

    public List<Product> getProductsInCart(int userId) {
        Cart cart = getOrCreateCart(userId);
        List<Product> productsInCart = new ArrayList<>();

        List<CartItem> cartItems = cartItemsRepository.findAllByCartId(cart.getCartId());

        for (CartItem cartItem : cartItems) {
            int productId = cartItem.getProductId();
            Product product = productService.getProductById(productId);

            if (product != null) {
                productsInCart.add(product);
            }
        }

        return productsInCart;
    }


    public BigDecimal calculateTotal(int cartId) {
        Cart cart = cartRepository.findCartByCartId(cartId);
        List<CartItem> cartItems = cartItemsRepository.findAllByCartId(cart.getCartId());

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            int productId = cartItem.getProductId();
            Product product = productService.getProductById(productId);

            if (product != null) {
                BigDecimal itemTotal = product.getPrice();
                total = total.add(itemTotal);
            }
        }

        return total;
    }

    public void removeFromCart(int cartItemId) {
        // Удаляем элемент корзины по его идентификатору
        cartRepository.deleteById(cartItemId);
    }

    public Cart getOrCreateCart(int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart);
        });
    }
}