package com.ran.spi.impl;

import com.ran.spi.MyServiceProvider;

/**
 * MyServiceProviderImpl2
 *
 * @author rwei
 * @since 2024/5/29 14:24
 */
public class MyServiceProviderImpl2 implements MyServiceProvider {
    @Override
    public String getName() {
        return "service 2";
    }
}
