package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.*;
import com.example.SpingOnlineSite.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The type Cart controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;


    /**
     * Instantiates a new Cart controller.
     *
     * @param cartService the cart service
     */
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Gets all items in cart.
     *
     * @return the all items in cart
     */
    @PostMapping
    public List<Cart> getAllItemsInCart() {
        return cartService.getAllItemsInCart();
    }

    @PostMapping("/add/cartId={cartId}/productId={productId}")
    public CartItem addItemToCart(@PathVariable int cartId, @PathVariable int productId) {
       return cartService.addItemToCart(cartId, productId);
    }

    @PostMapping("/create/userId={userId}")
    public Cart createCart(@PathVariable int userId)
    {
        return cartService.getOrCreateCart(userId);
    }


    @PostMapping("/getProductsInCart/userId={userId}")
    public List<Product> getProductsInCart(@PathVariable int userId)
    {
        return cartService.getProductsInCart(userId);
    }

    @PostMapping("/getTotalCost/cartId={cartId}")
    public BigDecimal getTotalCost(@PathVariable int cartId)
    {
        return cartService.calculateTotal(cartId);
    }


    /**
     * Show total string.
     *
     * @param model          the model
     * @param authentication the authentication
     * @return the string
     */

    /**
     * Remove from cart.
     *
     * @param cartId the cart id
     */
    @DeleteMapping("/remove/cartId={cartId}")
    public void removeFromCart(@PathVariable int cartId) {
        cartService.removeFromCart(cartId);
    }
}
