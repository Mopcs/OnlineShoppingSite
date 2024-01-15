package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.ProductImage;
import com.example.SpingOnlineSite.Service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/getAllProductImagesByProductId/productId={productId}")
    public List<ProductImage> getAllProductImagesByProductId(@PathVariable int productId)
    {
        return productImageService.getAllProductImagesByProductId(productId);
    }


    @PostMapping("/getFirstImage/productId={productId}")
    public  ResponseEntity<byte[]> getFirstImage(@PathVariable int productId) throws IOException {
        return productImageService.getFirstProductImageByProductId(productId);
    }

    @PostMapping("/getAllProductImages/productId={productId}")
    public ResponseEntity<List<byte[]>> getAll(@PathVariable int productId) throws IOException {
        return productImageService.getAllProductImages(productId);
    }

    @PostMapping("/createProductImage/productId={productId}")
    public List<ProductImage> createProductImages(@PathVariable int productId,  @RequestParam("imagePath") List<MultipartFile> files) throws IOException {
        return productImageService.createProductImages(productId, files);
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
