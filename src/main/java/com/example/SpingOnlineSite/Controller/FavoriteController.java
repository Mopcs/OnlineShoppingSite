package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Favorite;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Entity.ProductImage;
import com.example.SpingOnlineSite.Service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/getFavorites/userId={userId}")
    public List<Product> getFavoritesByUserId(@PathVariable int userId) {
        return favoriteService.getFavoritesProductsByUserId(userId);
    }

    @PostMapping("/addFavorite/userId={userId}/productId={productId}")
    public Favorite addFavoriteProduct(@PathVariable int userId, @PathVariable int productId) {
        return favoriteService.addFavoriteProduct(userId, productId);
    }

    @DeleteMapping("/remove/userId={userId}/productId={productId}")
    public void removeFavoriteProduct(@PathVariable int userId, @PathVariable int productId) {
        favoriteService.removeFavoriteProduct(userId,productId);
    }
}
