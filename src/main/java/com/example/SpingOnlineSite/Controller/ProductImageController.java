package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.ProductImage;
import com.example.SpingOnlineSite.Service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productImages")
public class ProductImageController {

    private final ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @GetMapping("/getAllProductImages")
    public List<ProductImage> getAllProductImages() {
        return productImageService.getAllProductImages();
    }

    @GetMapping("/getProductImageById/imageId={imageId}")
    public ProductImage getProductImageById(@PathVariable int imageId) {
        return productImageService.getProductImageById(imageId);
    }

    @PostMapping("/createProductImage/productId={productId}")
    public ProductImage createProductImage(@PathVariable int productId,@RequestBody ProductImage productImage) {
        return productImageService.createProductImage(productId, productImage);
    }

    @PutMapping("/updateProductImage/imageId={imageId}")
    public ProductImage updateProductImage(@PathVariable int imageId, @RequestBody ProductImage productImage) {
        return productImageService.updateProductImage(imageId, productImage);
    }

    @DeleteMapping("/deleteProductImage/imageId={imageId}")
    public void deleteProductImage(@PathVariable int imageId) {
        productImageService.deleteProductImage(imageId);
    }
}
