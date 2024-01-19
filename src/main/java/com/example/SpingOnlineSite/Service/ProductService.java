package com.example.SpingOnlineSite.Service;

import com.example.SpingOnlineSite.Entity.Order;
import com.example.SpingOnlineSite.Entity.OrderStatus;
import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Entity.User;
import com.example.SpingOnlineSite.Repository.OrderRepository;
import com.example.SpingOnlineSite.Repository.ProductRepository;
import com.example.SpingOnlineSite.Repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден по id: " + productId));
    }

    public List<Product> searchProducts(String size, String category, String productType, String condition, String chapter, String color, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Order> acceptedOrders = orderRepository.findByStatus(OrderStatus.ACCEPTED);

        List<Integer> productIds = acceptedOrders.stream()
                .map(Order::getProductId)
                .collect(Collectors.toList());

        return productRepository.search(size, category, productType, condition, chapter, color, minPrice, maxPrice, productIds);
    }

    public List<String> getAllProductNames() {
        return productRepository.findAllProductNames();
    }

    public Product createProduct(Product product, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден по имени: " + userId));

        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setUserId(user.getUserId());

        return productRepository.save(product);
    }

    public Product updateProduct(int productId, Product product) {
        Product existingProduct = getProductById(productId);

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setUserId(product.getUserId());

        return productRepository.save(existingProduct);
    }

    public List<Product> getAcceptedProducts() {
        List<Order> acceptedOrders = orderRepository.findByStatus(OrderStatus.ACCEPTED);

        List<Integer> productIds = acceptedOrders.stream()
                .map(Order::getProductId)
                .collect(Collectors.toList());

        return productRepository.findAllById(productIds);
    }

    public Integer getProductIdByUserId(int userId)
    {
        return productRepository.findProductIdByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Такого продукта нет"));
    }

    public void deleteProduct(int productId) {
        getProductById(productId);

        productRepository.deleteById(productId);
    }

    public Integer getIdByName(String productName)
    {
        return productRepository.getIdByName(productName);
    }
}