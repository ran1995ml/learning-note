package com.ran.pattern.proxy;

/**
 * RealImage
 *
 * @author rwei
 * @since 2024/9/9 10:22
 */
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("loading " + fileName);
    }
}
