package com.example.SpingOnlineSite.Service;

import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Entity.ProductImage;
import com.example.SpingOnlineSite.Repository.ProductImageRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductService productService;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository, ProductService productService) {
        this.productImageRepository = productImageRepository;
        this.productService = productService;
    }

    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    public ProductImage getProductImageById(int imageId) {
        return productImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Изображение продукта не найдено по id: " + imageId));
    }

    public ProductImage createProductImage(int productId, ProductImage productImage) {
        Product product = productService.getProductById(productId);

        if (product == null) {
            throw new ResourceNotFoundException("Продукт не найден по id: " + productId);
        }

        productImage.setProductId(productId);
        productImage.setImagePath(productImage.getImagePath());
        productImage.setDescription(productImage.getDescription());

        return productImageRepository.save(productImage);
    }

    public ProductImage updateProductImage(int imageId, ProductImage productImage) {
        ProductImage existingImage = getProductImageById(imageId);

        existingImage.setImagePath(productImage.getImagePath());
        existingImage.setDescription(productImage.getDescription());

        return productImageRepository.save(existingImage);
    }

    public void deleteProductImage(int imageId) {
        getProductImageById(imageId);

        productImageRepository.deleteById(imageId);
    }
}
