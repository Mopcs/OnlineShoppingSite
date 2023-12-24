package com.example.SpingOnlineSite.Service;

import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Entity.User;
import com.example.SpingOnlineSite.Repository.ProductRepository;
import com.example.SpingOnlineSite.Repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден по id: " + productId));
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

    public void deleteProduct(int productId) {
        getProductById(productId);

        productRepository.deleteById(productId);
    }

    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }
}