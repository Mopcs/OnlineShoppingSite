package com.example.SpingOnlineSite.Service;


import com.example.SpingOnlineSite.Entity.Favorite;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Entity.ProductImage;
import com.example.SpingOnlineSite.Repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Favorite service.
 */
@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    private final ProductService productService;

    /**
     * Instantiates a new Favorite service.
     *
     * @param favoriteRepository the favorite repository
     */
    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, ProductService productService) {
        this.favoriteRepository = favoriteRepository;
        this.productService = productService;
    }

    /**
     * Gets favorites by user id.
     *
     * @param userId the user id
     * @return the favorites by user id
     */
    public List<Product> getFavoritesProductsByUserId(int userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        List<Product> favProducts = new ArrayList<>();

        for (Favorite favorite : favorites) {
            Product product = productService.getProductById(favorite.getProductId());

            favProducts.add(product);
        }

        return favProducts;
    }

    /**
     * Add favorite product favorite.
     *
     * @param userId    the user id
     * @param productId the product id
     * @return the favorite
     */
    public Favorite addFavoriteProduct(int userId, int productId) {

        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setProductId(productId);

        return favoriteRepository.save(favorite);
    }

    /**
     * Remove favorite product.
     *
     * @param favoriteId the favorite id
     */
    public void removeFavoriteProduct(int favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }
}
