package com.example.SpingOnlineSite.Service;


import com.example.SpingOnlineSite.Entity.Favorite;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Entity.ProductImage;
import com.example.SpingOnlineSite.Repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    private final ProductService productService;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, ProductService productService) {
        this.favoriteRepository = favoriteRepository;
        this.productService = productService;
    }

    public List<Product> getFavoritesProductsByUserId(int userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        List<Product> favProducts = new ArrayList<>();

        for (Favorite favorite : favorites) {
            Product product = productService.getProductById(favorite.getProductId());

            favProducts.add(product);
        }

        return favProducts;
    }

    public Favorite addFavoriteProduct(int userId, int productId) {

        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setProductId(productId);

        return favoriteRepository.save(favorite);
    }

    public void removeFavoriteProduct(int userId, int productId) {
        Optional<Favorite> favoriteOptional = favoriteRepository.findByUserIdAndProductId(userId, productId);

        favoriteOptional.ifPresent(favoriteRepository::delete);
    }
}
