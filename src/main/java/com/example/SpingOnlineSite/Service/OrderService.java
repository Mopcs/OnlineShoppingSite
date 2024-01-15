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

/**
 * The type Order service.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    /**
     * Instantiates a new Order service.
     *
     * @param orderRepository   the order repository
     * @param productRepository the product repository
     */
    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /**
     * Gets all orders.
     *
     * @return the all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Gets order by id.
     *
     * @param orderId the order id
     * @return the order by id
     */
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Заказ не найден по id: " + orderId));
    }

    /**
     * Create order order.
     *
     * @param order     the order
     * @param productId the product id
     * @return the order
     */
    public Order createOrder(Order order, int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден по имени: " + productId));

        order.setUserId(product.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setProductId(productId);

        return orderRepository.save(order);
    }

    /**
     * Approve order.
     *
     * @param orderId the order id
     */
    public void approveOrder(int orderId) {
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
    }

    /**
     * Reject order.
     *
     * @param orderId the order id
     */
    public void rejectOrder(int orderId) {
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.REJECTED);
        orderRepository.save(order);
        deleteOrder(orderId);
    }

    /**
     * Gets orders by status.
     *
     * @param status the status
     * @return the orders by status
     */
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    /**
     * Update order order.
     *
     * @param orderId the order id
     * @param order   the order
     * @return the order
     */
    public Order updateOrder(int orderId, Order order) {
        Order existingOrder = getOrderById(orderId);

        existingOrder.setUserId(order.getUserId());
        existingOrder.setProductId(order.getProductId());
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
    }

    /**
     * Delete order.
     *
     * @param orderId the order id
     */
    public void deleteOrder(int orderId) {
        getOrderById(orderId);

        orderRepository.deleteById(orderId);
    }
}
