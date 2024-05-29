package com.ran.spi;

import java.util.ServiceLoader;

/**
 * ServiceDiscovery
 *
 * @author rwei
 * @since 2024/5/29 14:26
 */
public class ServiceDiscovery {
    public static void main(String[] args) {
        ServiceLoader<MyServiceProvider> serverLoader = ServiceLoader.load(MyServiceProvider.class);
        for (MyServiceProvider serviceProvider : serverLoader) {
            System.out.println(serviceProvider.getName());
        }
    }
}
