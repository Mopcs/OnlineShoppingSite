package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Favorite;
import com.example.SpingOnlineSite.Service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Favorite> getFavoritesByUserId(@PathVariable int userId) {
        return favoriteService.getFavoritesByUserId(userId);
    }

    @PostMapping("/addFavorite/userId={userId}/productId={productId}")
    public Favorite addFavoriteProduct(@PathVariable int userId, @PathVariable int productId) {
        return favoriteService.addFavoriteProduct(userId, productId);
    }

    @DeleteMapping("/remove/{favoriteId}")
    public ResponseEntity<String> removeFavoriteProduct(@PathVariable int favoriteId) {
        favoriteService.removeFavoriteProduct(favoriteId);
        return new ResponseEntity<>("Product removed from favorites.", HttpStatus.OK);
    }
}
