package com.example.SpingOnlineSite.Entity;

import lombok.Getter;

import javax.persistence.*;

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

    @Column(name = "description")
    private String description;

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
