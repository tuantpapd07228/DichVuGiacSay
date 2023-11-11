package com.example.dichvugiacsay.Model;

public class User_CardView {
    private int resourceImage;
    private String name;

    public User_CardView() {
    }

    public User_CardView(int resourceImage, String name) {
        this.resourceImage = resourceImage;
        this.name = name;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
