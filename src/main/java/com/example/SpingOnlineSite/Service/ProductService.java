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

/**
 * The type Product service.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    /**
     * Instantiates a new Product service.
     *
     * @param productRepository the product repository
     * @param userRepository    the user repository
     */
    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Gets all products.
     *
     * @return the all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Gets product by id.
     *
     * @param productId the product id
     * @return the product by id
     */
    public Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден по id: " + productId));
    }

    /**
     * Create product product.
     *
     * @param product the product
     * @param userId  the user id
     * @return the product
     */
    public Product createProduct(Product product, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден по имени: " + userId));

        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setUserId(user.getUserId());

        return productRepository.save(product);
    }

    /**
     * Update product product.
     *
     * @param productId the product id
     * @param product   the product
     * @return the product
     */
    public Product updateProduct(int productId, Product product) {
        Product existingProduct = getProductById(productId);

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setUserId(product.getUserId());

        return productRepository.save(existingProduct);
    }

    /**
     * Gets product id by user id.
     *
     * @param userId the user id
     * @return the product id by user id
     */
    public Integer getProductIdByUserId(int userId)
    {
        return productRepository.findProductIdByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Такого продукта нет"));
    }

    /**
     * Delete product.
     *
     * @param productId the product id
     */
    public void deleteProduct(int productId) {
        getProductById(productId);

        productRepository.deleteById(productId);
    }

    /**
     * Find product by name optional.
     *
     * @param name the name
     * @return the optional
     */
    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }
}