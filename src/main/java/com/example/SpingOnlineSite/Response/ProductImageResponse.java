package com.example.SpingOnlineSite.Response;

public class ProductImageResponse {
    private final String imagePath;
    private final byte[] imageBytes;

    public ProductImageResponse(String imagePath, byte[] imageBytes) {
        this.imagePath = imagePath;
        this.imageBytes = imageBytes;
    }

    public String getImagePath() {
        return imagePath;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }
}
