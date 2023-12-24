package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Service.ProductService;
import com.example.SpingOnlineSite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/productId={productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/createProduct/userId={userId}")
    public Product createProduct(@RequestBody Product product, @PathVariable int userId) {
        return productService.createProduct(product, userId);
    }

    @PutMapping("/updateProduct/productId={productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/deleteProduct/productId={productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/findProductByName/productName={productName}")
    public ResponseEntity<Product> findProductByName(@PathVariable String productName) {
        Optional<Product> product = productService.findProductByName(productName);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
