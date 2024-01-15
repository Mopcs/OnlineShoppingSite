package com.example.SpingOnlineSite.Entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.File;

/**
 * The type Product image.
 */
@Getter
@Entity
@Table(name = "product_images")
public class ProductImage {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @JoinColumn(name = "product_id")
    private int productId;


    @Column(name = "image_path")
    private String imagePath;


    /**
     * Sets image id.
     *
     * @param imageId the image id
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Sets image path.
     *
     * @param imagePath the image path
     */
    public void setImagePath(String imagePath) { this.imagePath = imagePath;}


    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
