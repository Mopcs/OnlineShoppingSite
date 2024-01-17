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

/**
 * The type Favorite controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    /**
     * Instantiates a new Favorite controller.
     *
     * @param favoriteService the favorite service
     */
    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    /**
     * Gets favorites by user id.
     *
     * @param userId the user id
     * @return the favorites by user id
     */
    @PostMapping("/getFavorites/userId={userId}")
    public List<Product> getFavoritesByUserId(@PathVariable int userId) {
        return favoriteService.getFavoritesProductsByUserId(userId);
    }

    /**
     * Add favorite product favorite.
     *
     * @param userId    the user id
     * @param productId the product id
     * @return the favorite
     */
    @PostMapping("/addFavorite/userId={userId}/productId={productId}")
    public Favorite addFavoriteProduct(@PathVariable int userId, @PathVariable int productId) {
        return favoriteService.addFavoriteProduct(userId, productId);
    }

    /**
     * Remove favorite product response entity.
     *
     * @param favoriteId the favorite id
     * @return the response entity
     */
    @DeleteMapping("/remove/{favoriteId}")
    public ResponseEntity<String> removeFavoriteProduct(@PathVariable int favoriteId) {
        favoriteService.removeFavoriteProduct(favoriteId);
        return new ResponseEntity<>("Product removed from favorites.", HttpStatus.OK);
    }
}
