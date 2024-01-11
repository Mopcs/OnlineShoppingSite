package com.example.SpingOnlineSite.Service;

import com.example.SpingOnlineSite.Entity.Order;
import com.example.SpingOnlineSite.Entity.OrderStatus;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Repository.OrderRepository;
import com.example.SpingOnlineSite.Repository.ProductRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Заказ не найден по id: " + orderId));
    }

    public Order createOrder(Order order, int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден по имени: " + productId));

        order.setUserId(product.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setProductId(productId);

        return orderRepository.save(order);
    }

    /*public Order createOrder(Order order) {

        order.setUserId(order.getProduct().getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setProduct(order.getProduct());

        return orderRepository.save(order);
    }*/

    /*public Order createOrderWithProduct(Order order) {

        order.setUserId(order.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setProduct(order.getProduct());

        return orderRepository.save(order);
    }*/

    public void approveOrder(int orderId) {
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
    }

    public void rejectOrder(int orderId) {
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.REJECTED);
        orderRepository.save(order);
        deleteOrder(orderId);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public Order updateOrder(int orderId, Order order) {
        Order existingOrder = getOrderById(orderId);

        existingOrder.setUserId(order.getUserId());
        existingOrder.setProductId(order.getProductId());
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(int orderId) {
        getOrderById(orderId);

        orderRepository.deleteById(orderId);
    }
}
