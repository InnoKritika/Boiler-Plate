package com.example.boilerplate.ModelClasses;

import java.io.Serializable;

public class Image implements Serializable {
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image() {
    }
}
