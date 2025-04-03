package com.example;


import java.util.ArrayList;

public class Repository {

    private static ArrayList<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        images.add(image);
    }

    public ArrayList<Image> getImages() {
        return images;
    }

}
