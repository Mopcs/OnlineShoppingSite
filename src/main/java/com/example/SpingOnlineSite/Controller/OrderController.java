package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Order;
import com.example.SpingOnlineSite.Entity.OrderStatus;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Service.OrderService;
import com.example.SpingOnlineSite.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Order controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    /**
     * Instantiates a new Order controller.
     *
     * @param orderService   the order service
     * @param productService the product service
     */
    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    /**
     * Gets all orders.
     *
     * @return the all orders
     */
    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Gets pending orders.
     *
     * @return the pending orders
     */
    @GetMapping("/getPendingOrders")
    public List<Order> getPendingOrders() {
        return orderService.getOrdersByStatus(OrderStatus.PENDING);
    }

    /**
     * Gets order details.
     *
     * @param order_id the order id
     * @return the order details
     */
    @GetMapping("/getOrderDetails/{order_id}")
    public Product getOrderDetails(@PathVariable int order_id)
    {
        Order order = orderService.getOrderById(order_id);
        return productService.getProductById(order.getProductId());
    }

    /**
     * Gets approved orders.
     *
     * @return the approved orders
     */
    @GetMapping("/getApprovedOrders")
    public List<Order> getApprovedOrders() {
        return orderService.getOrdersByStatus(OrderStatus.ACCEPTED);
    }

    /**
     * Gets rejected orders.
     *
     * @return the rejected orders
     */
    @GetMapping("/getRejectedOrders")
    public List<Order> getRejectedOrders() {
        return orderService.getOrdersByStatus(OrderStatus.REJECTED);
    }

    /**
     * Gets order by id.
     *
     * @param orderId the order id
     * @return the order by id
     */
    @PostMapping("/getOrderById/orderId={orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    /**
     * Create order order.
     *
     * @param order     the order
     * @param productId the product id
     * @return the order
     */
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

    /**
     * Approve order.
     *
     * @param orderId the order id
     */
    @PostMapping("/approveOrder/orderId={orderId}")
    public void approveOrder(@PathVariable int orderId) {
        orderService.approveOrder(orderId);
    }

    /**
     * Reject order.
     *
     * @param orderId the order id
     */
    @PostMapping("/rejectOrder/orderId={orderId}")
    public void rejectOrder(@PathVariable int orderId) {
        orderService.rejectOrder(orderId);
    }

    /**
     * Update order order.
     *
     * @param orderId the order id
     * @param order   the order
     * @return the order
     */
    @PutMapping("/updateOrder/orderId={orderId}")
    public Order updateOrder(@PathVariable int orderId, @RequestBody Order order) {
        return orderService.updateOrder(orderId, order);
    }

    /**
     * Delete order.
     *
     * @param orderId the order id
     */
    @DeleteMapping("/deleteOrder/orderId={orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }
}
