package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Cart;
import com.example.SpingOnlineSite.Entity.CartItem;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public List<Cart> getAllItemsInCart() {
        return cartService.getAllItemsInCart();
    }

    @PostMapping("/add/userId={userId}/productId={productId}")
    public CartItem addItemToCart(@PathVariable int userId, @PathVariable int productId) {
       return cartService.addItemToCart(userId, productId);
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

    @DeleteMapping("/remove/userId={userId}/productId={productId}")
    public void removeFromCart(@PathVariable int userId, @PathVariable int productId) {
        cartService.removeCartItem(userId, productId);
    }
}
