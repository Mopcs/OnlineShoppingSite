package com.example.SpingOnlineSite.Service;

import com.example.SpingOnlineSite.Entity.Product;
import com.example.SpingOnlineSite.Entity.ProductImage;
import com.example.SpingOnlineSite.Repository.ProductImageRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

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

    public List<ProductImage> getAllProductImagesByProductId(int productId) {
        return productImageRepository.findByProductId(productId);
    }

    public List<ProductImage> createProductImages(int productId, List<MultipartFile> files) throws IOException {
        Product product = productService.getProductById(productId);

        if (product == null) {
            throw new ResourceNotFoundException("Продукт не найден по id: " + productId);
        }

        List<ProductImage> createdImages = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + ".jpg";

            saveImage(file, fileName);

            ProductImage productImage = new ProductImage();
            productImage.setProductId(productId);
            productImage.setImagePath("Images/products/" + fileName);

            createdImages.add(productImageRepository.save(productImage));
        }

        return createdImages;
    }

    public void saveImage(MultipartFile file, String fileName) throws IOException {
        String resourcesDirectory = "C:\\Users\\baron\\OneDrive\\Рабочий стол\\SpingOnlineSite\\src\\main\\resources\\Images\\products";

        Path directory = Paths.get(resourcesDirectory);

        Files.createDirectories(directory);

        Path filePath = directory.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    public ResponseEntity<byte[]> getFirstProductImageByProductId(int productId) throws IOException {
        List<ProductImage> productImages = getAllProductImagesByProductId(productId);

        if (!productImages.isEmpty()) {
            ProductImage firstImage = productImages.get(0);

            String imagePath = "C:\\Users\\baron\\OneDrive\\Рабочий стол\\SpingOnlineSite\\src\\main\\resources\\" + firstImage.getImagePath();

            Path path = Paths.get(imagePath);
            byte[] imageBytes = Files.readAllBytes(path);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } else {
            throw new ResourceNotFoundException("У продукта с id " + productId + " нет изображений");
        }
    }

    public ResponseEntity<List<byte[]>> getAllProductImages(int productId) throws IOException {
        List<ProductImage> productImages = getAllProductImagesByProductId(productId);
        List<byte[]> imageBytesList = new ArrayList<>();

        for (ProductImage productImage : productImages) {
            String imagePath = "C:\\Users\\baron\\OneDrive\\Рабочий стол\\SpingOnlineSite\\src\\main\\resources\\" + productImage.getImagePath();

            Path path = Paths.get(imagePath);

            if (Files.exists(path)) {
                byte[] imageBytes = Files.readAllBytes(path);
                imageBytesList.add(imageBytes);
            } else {
                imageBytesList.add(null);
            }
        }

        if (!imageBytesList.isEmpty()) {
            return new ResponseEntity<>(imageBytesList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public String getFirstProductImageUrlByProductId(int productId) {
        List<ProductImage> productImages = getAllProductImagesByProductId(productId);

        if (!productImages.isEmpty()) {
            ProductImage firstImage = productImages.get(0);
            return getResourceImageUrl(firstImage.getImagePath());
        } else {
            throw new ResourceNotFoundException("У продукта с id " + productId + " нет изображений");
        }
    }

    public List<String> getAllProductImageUrlsByProductId(int productId) {
        List<ProductImage> productImages = getAllProductImagesByProductId(productId);
        List<String> imageUrls = new ArrayList<>();

        for (ProductImage productImage : productImages) {
            String imageUrl = getResourceImageUrl(productImage.getImagePath());
            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }

    private String getResourceImageUrl(String imagePath) {
        return "/"+imagePath;
    }

    public ProductImage updateProductImage(int imageId, ProductImage productImage) {
        ProductImage existingImage = getProductImageById(imageId);

        existingImage.setImagePath(productImage.getImagePath());

        return productImageRepository.save(existingImage);
    }

    public void deleteProductImage(int imageId) {
        getProductImageById(imageId);

        productImageRepository.deleteById(imageId);
    }
}
