package com.example.SpingOnlineSite.Repository;

import com.example.SpingOnlineSite.Entity.Order;
import com.example.SpingOnlineSite.Entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByStatus(OrderStatus status);
}