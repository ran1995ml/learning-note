package com.ran.spi.impl;

import com.ran.spi.MyServiceProvider;

/**
 * MyServiceProviderImpl1
 *
 * @author rwei
 * @since 2024/5/29 14:22
 */
public class MyServiceProviderImpl1 implements MyServiceProvider {
    @Override
    public String getName() {
        return "service 1";
    }
}
