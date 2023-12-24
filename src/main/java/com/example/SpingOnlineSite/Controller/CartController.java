package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Cart;
import com.example.SpingOnlineSite.Entity.CartItem;
import com.example.SpingOnlineSite.Entity.User;
import com.example.SpingOnlineSite.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getAllItemsInCart() {
        return cartService.getAllItemsInCart();
    }

    @PostMapping("/add/userId={userId}/productId={productId}")
    public void addItemToCart(@PathVariable int userId, @PathVariable int productId, @RequestBody CartItem cartItem) {
        //cartItem.setQuantity(cartItem.getQuantity());
        cartService.addItemToCart(userId, productId, cartItem.getQuantity());
    }

    @GetMapping("/total")
    public String showTotal(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<Cart> cart = cartService.getCartByUserId(user.getUserId());
        BigDecimal total = cartService.calculateTotal(cart);
        model.addAttribute("total", total);

        return "cart/total";
    }

    @DeleteMapping("/remove/cartId={cartId}")
    public void removeFromCart(@PathVariable int cartId) {
        cartService.removeFromCart(cartId);
    }
}
