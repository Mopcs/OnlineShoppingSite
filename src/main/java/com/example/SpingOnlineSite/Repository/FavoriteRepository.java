package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(int userId);

    Optional<Favorite> findByUserIdAndProductId(int userId, int productId);
}
