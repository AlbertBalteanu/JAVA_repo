package com.example;

import java.awt.Desktop;

public class App {
    public static void main(String[] args) {

        Repository repository = new Repository();
        Image image1 = new Image("image1", "D:/saishomeet2022/image00018.jpeg");
        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
            desktop.open(new java.io.File(image1.getLocation()));
            } else {
            System.out.println("Open action is not supported on this platform.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(repository.getImages());

    }
}
