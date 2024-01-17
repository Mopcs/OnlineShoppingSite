package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Cart;
import com.example.SpingOnlineSite.Entity.CartItem;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    /**
     * Add item to cart cart item.
     *
     * @param cartId    the cart id
     * @param productId the product id
     * @return the cart item
     */
    @PostMapping("/add/userId={userId}/productId={productId}")
    public CartItem addItemToCart(@PathVariable int userId, @PathVariable int productId) {
       return cartService.addItemToCart(userId, productId);
    }

    /**
     * Create cart cart.
     *
     * @param userId the user id
     * @return the cart
     */
    @PostMapping("/create/userId={userId}")
    public Cart createCart(@PathVariable int userId)
    {
        return cartService.getOrCreateCart(userId);
    }


    /**
     * Gets products in cart.
     *
     * @param userId the user id
     * @return the products in cart
     */
    @PostMapping("/getProductsInCart/userId={userId}")
    public List<Product> getProductsInCart(@PathVariable int userId)
    {
        return cartService.getProductsInCart(userId);
    }

    /**
     * Gets total cost.
     *
     * @param cartId the cart id
     * @return the total cost
     */
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
