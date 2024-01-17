package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.Favorite;
import com.example.SpingOnlineSite.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(int userId);
}
