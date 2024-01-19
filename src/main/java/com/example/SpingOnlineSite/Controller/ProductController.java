package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Service.ProductService;
import com.example.SpingOnlineSite.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/getIdByProductName/productName={productName}")
    public Integer getId(@PathVariable String productName)
    {
        return productService.getIdByName(productName);
    }

    @PostMapping("/getProductById/productId={productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/createProduct/userId={userId}")
    public Product createProduct(@RequestBody Product product, @PathVariable int userId) {
        return productService.createProduct(product, userId);
    }

    @PostMapping("/getProductIdByUserId/userId={userId}")
    public ResponseEntity<Integer> findProductIdByUserId(@PathVariable int userId)
    {
        Integer productId = productService.getProductIdByUserId(userId);
        return ResponseEntity.ok(productId);
    }

    @PostMapping("/getAcceptedProducts")
    public List<Product> getAcceptedProducts()
    {
        return productService.getAcceptedProducts();
    }

    @PostMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(name = "size", required = false) String size,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "productType", required = false) String productType,
            @RequestParam(name = "condition", required = false) String condition,
            @RequestParam(name = "chapter", required = false) String chapter,
            @RequestParam(name = "color", required = false) String color,
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice
    ) {
        return productService.searchProducts(size, category, productType, condition, chapter, color, minPrice, maxPrice);
    }
    @PutMapping("/updateProduct/productId={productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/deleteProduct/productId={productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    @PostMapping("/getAllProductsNames")
    public List<String> findProductByName() {
        return productService.getAllProductNames();
    }
}
