package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Service.ProductService;
import com.example.SpingOnlineSite.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Product controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all products.
     *
     * @return the all products
     */
    @PostMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Gets product by id.
     *
     * @param productId the product id
     * @return the product by id
     */
    @PostMapping("/getProductById/productId={productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    /**
     * Create product product.
     *
     * @param product the product
     * @param userId  the user id
     * @return the product
     */
    @PostMapping("/createProduct/userId={userId}")
    public Product createProduct(@RequestBody Product product, @PathVariable int userId) {
        return productService.createProduct(product, userId);
    }

    /**
     * Find product id by user id response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @PostMapping("/getProductIdByUserId/userId={userId}")
    public ResponseEntity<Integer> findProductIdByUserId(@PathVariable int userId)
    {
        Integer productId = productService.getProductIdByUserId(userId);
        return ResponseEntity.ok(productId);
    }

    /**
     * Update product product.
     *
     * @param productId the product id
     * @param product   the product
     * @return the product
     */
    @PutMapping("/updateProduct/productId={productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    /**
     * Delete product.
     *
     * @param productId the product id
     */
    @DeleteMapping("/deleteProduct/productId={productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    /**
     * Find product by name response entity.
     *
     * @param productName the product name
     * @return the response entity
     */
    @GetMapping("/findProductsByName/productName={productName}")
    public List<Product> findProductByName(@PathVariable String productName) {
        return productService.findProductByName(productName);
    }

    @GetMapping("/findProductsByGender/gender={gender}")
    public List<Product> findProductsByGender(@PathVariable String gender)
    {
        return productService.getProductsByGender(gender);
    }
}
