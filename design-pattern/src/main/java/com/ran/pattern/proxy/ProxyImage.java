package com.ran.pattern.proxy;

import java.util.Objects;

/**
 * ProxyImage
 *
 * @author rwei
 * @since 2024/9/9 10:24
 */
public class ProxyImage implements Image {
    private RealImage realImage;

    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (Objects.isNull(realImage)) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
