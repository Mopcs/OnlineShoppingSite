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

/**
 * The type Cart service.
 */
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemsRepository cartItemsRepository;

    /**
     * Instantiates a new Cart service.
     *
     * @param cartRepository      the cart repository
     * @param productService      the product service
     * @param cartItemsRepository the cart items repository
     */
    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService, CartItemsRepository cartItemsRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemsRepository = cartItemsRepository;
    }

    /**
     * Gets all items in cart.
     *
     * @return the all items in cart
     */
    public List<Cart> getAllItemsInCart() {
        return cartRepository.findAll();
    }

    /**
     * Add item to cart cart item.
     *
     * @param cartId    the cart id
     * @param productId the product id
     * @return the cart item
     */
    public CartItem addItemToCart(int userId, int productId) {
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProductId(productId);

        return cartItemsRepository.save(cartItem);
    }

    /**
     * Gets products in cart.
     *
     * @param userId the user id
     * @return the products in cart
     */
    public List<Product> getProductsInCart(int userId) {
        List<Product> productsInCart = new ArrayList<>();

        // Получаем все элементы корзины для указанного пользователя
        List<CartItem> cartItems = cartItemsRepository.findAllByUserId(userId);

        // Обходим каждый элемент корзины и получаем соответствующий продукт
        for (CartItem cartItem : cartItems) {
            int productId = cartItem.getProductId();
            Product product = productService.getProductById(productId);

            // Если продукт существует, добавляем его в список
            if (product != null) {
                productsInCart.add(product);
            }
        }

        return productsInCart;
    }


    /**
     * Calculate total big decimal.
     *
     * @param cartId the cart id
     * @return the big decimal
     */
    public BigDecimal calculateTotal(int userId) {
        List<CartItem> cartItems = cartItemsRepository.findAllByUserId(userId);
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


    /**
     * Remove from cart.
     *
     * @param cartItemId the cart item id
     */
    public void removeFromCart(int cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    /**
     * Gets or create cart.
     *
     * @param userId the user id
     * @return the or create cart
     */
    public Cart getOrCreateCart(int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart);
        });
    }
}