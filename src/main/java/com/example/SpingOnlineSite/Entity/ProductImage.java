package com.example.SpingOnlineSite.Entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.File;

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



    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setImagePath(String imagePath) { this.imagePath = imagePath;}


    public void setProductId(int productId) {
        this.productId = productId;
    }
}
