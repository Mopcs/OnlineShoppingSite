package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Order;
import com.example.SpingOnlineSite.Entity.OrderStatus;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Service.OrderService;
import com.example.SpingOnlineSite.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getPendingOrders")
    public List<Order> getPendingOrders() {
        return orderService.getOrdersByStatus(OrderStatus.PENDING);
    }

    @GetMapping("/getOrderDetails/{order_id}")
    public Product getOrderDetails(@PathVariable int order_id)
    {
        Order order = orderService.getOrderById(order_id);
        return productService.getProductById(order.getProductId());
    }

    @GetMapping("/getApprovedOrders")
    public List<Order> getApprovedOrders() {
        return orderService.getOrdersByStatus(OrderStatus.ACCEPTED);
    }

    @GetMapping("/getRejectedOrders")
    public List<Order> getRejectedOrders() {
        return orderService.getOrdersByStatus(OrderStatus.REJECTED);
    }

    @GetMapping("/getOrderById/orderId={orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/createOrder/productId={productId}")
    public Order createOrder(@RequestBody Order order, @PathVariable int productId) {
        return orderService.createOrder(order, productId);
    }

    /*@PostMapping("/createOrder")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PostMapping("/createOrderWithProduct")
    public Order creteOrderWithProduct(@RequestBody Order order)
    {
        return orderService.createOrderWithProduct(order);
    }*/

    @PostMapping("/approveOrder/orderId={orderId}")
    public void approveOrder(@PathVariable int orderId) {
        orderService.approveOrder(orderId);
    }

    @PostMapping("/rejectOrder/orderId={orderId}")
    public void rejectOrder(@PathVariable int orderId) {
        orderService.rejectOrder(orderId);
    }

    @PutMapping("/updateOrder/orderId={orderId}")
    public Order updateOrder(@PathVariable int orderId, @RequestBody Order order) {
        return orderService.updateOrder(orderId, order);
    }

    @DeleteMapping("/deleteOrder/orderId={orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }
}
