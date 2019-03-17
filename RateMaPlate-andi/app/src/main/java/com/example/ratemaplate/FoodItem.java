package com.example.ratemaplate;

public class FoodItem {
    private String imageName;
    private int image;

    public FoodItem(String imageName, int image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public int getImage() {
        return image;
    }
}
