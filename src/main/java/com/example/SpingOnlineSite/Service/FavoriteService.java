package com.example.SpingOnlineSite.Service;


import com.example.SpingOnlineSite.Entity.Favorite;
import com.example.SpingOnlineSite.Repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Favorite service.
 */
@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    /**
     * Instantiates a new Favorite service.
     *
     * @param favoriteRepository the favorite repository
     */
    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    /**
     * Gets favorites by user id.
     *
     * @param userId the user id
     * @return the favorites by user id
     */
    public List<Favorite> getFavoritesByUserId(int userId) {
        return favoriteRepository.findByUserId(userId);
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
