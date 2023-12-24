package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Order;
import com.example.SpingOnlineSite.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderById/orderId={orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/createOrder/productName={productName}")
    public Order createOrder(@RequestBody Order order, @PathVariable String productName) {
        return orderService.createOrder(order, productName);
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
