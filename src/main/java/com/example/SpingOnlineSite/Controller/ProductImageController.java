package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.ProductImage;
import com.example.SpingOnlineSite.Service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The type Product image controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/productImages")
public class ProductImageController {

    private final ProductImageService productImageService;

    /**
     * Instantiates a new Product image controller.
     *
     * @param productImageService the product image service
     */
    @Autowired
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    /**
     * Gets all product images.
     *
     * @return the all product images
     */
    @GetMapping("/getAllProductImages")
    public List<ProductImage> getAllProductImages() {
        return productImageService.getAllProductImages();
    }

    /**
     * Gets product image by id.
     *
     * @param imageId the image id
     * @return the product image by id
     */
    @GetMapping("/getProductImageById/imageId={imageId}")
    public ProductImage getProductImageById(@PathVariable int imageId) {
        return productImageService.getProductImageById(imageId);
    }

    /**
     * Gets all product images by product id.
     *
     * @param productId the product id
     * @return the all product images by product id
     */
    @GetMapping("/getAllProductImagesByProductId/productId={productId}")
    public List<ProductImage> getAllProductImagesByProductId(@PathVariable int productId)
    {
        return productImageService.getAllProductImagesByProductId(productId);
    }


    /**
     * Gets first image.
     *
     * @param productId the product id
     * @return the first image
     * @throws IOException the io exception
     */
    @GetMapping("/getFirstImage/productId={productId}")
    public  ResponseEntity<byte[]> getFirstImage(@PathVariable int productId) throws IOException {
        return productImageService.getFirstProductImageByProductId(productId);
    }


    @GetMapping("/getFirstProductImage/productId={productId}")
    public String getFirstProductImage(@PathVariable int productId) {
        return  productImageService.getFirstProductImageUrlByProductId(productId);
    }

    @GetMapping("/getAllProductImagesURL/productId={productId}")
    public List<String> getAllProductImageUrls(@PathVariable int productId)
    {
        return productImageService.getAllProductImageUrlsByProductId(productId);
    }

    /**
     * Gets all.
     *
     * @param productId the product id
     * @return the all
     * @throws IOException the io exception
     */
    @GetMapping("/getAllProductImages/productId={productId}")
    public ResponseEntity<List<byte[]>> getAll(@PathVariable int productId) throws IOException {
        return productImageService.getAllProductImages(productId);
    }

    /**
     * Create product images list.
     *
     * @param productId the product id
     * @param files     the files
     * @return the list
     * @throws IOException the io exception
     */
    @PostMapping("/createProductImage/productId={productId}")
    public List<ProductImage> createProductImages(@PathVariable int productId,  @RequestParam("imagePath") List<MultipartFile> files) throws IOException {
        return productImageService.createProductImages(productId, files);
    }

    /**
     * Update product image product image.
     *
     * @param imageId      the image id
     * @param productImage the product image
     * @return the product image
     */
    @PutMapping("/updateProductImage/imageId={imageId}")
    public ProductImage updateProductImage(@PathVariable int imageId, @RequestBody ProductImage productImage) {
        return productImageService.updateProductImage(imageId, productImage);
    }

    /**
     * Delete product image.
     *
     * @param imageId the image id
     */
    @DeleteMapping("/deleteProductImage/imageId={imageId}")
    public void deleteProductImage(@PathVariable int imageId) {
        productImageService.deleteProductImage(imageId);
    }
}
