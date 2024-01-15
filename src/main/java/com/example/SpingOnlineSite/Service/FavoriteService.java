package com.example.SpingOnlineSite.Service;


import com.example.SpingOnlineSite.Entity.Favorite;
import com.example.SpingOnlineSite.Repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getFavoritesByUserId(int userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public Favorite addFavoriteProduct(int userId, int productId) {

        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setProductId(productId);

        return favoriteRepository.save(favorite);
    }

    public void removeFavoriteProduct(int favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }
}
