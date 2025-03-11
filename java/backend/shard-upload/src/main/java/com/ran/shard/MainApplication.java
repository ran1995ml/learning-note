package com.ran.shard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MainApplication
 *
 * @author rwei
 * @since 2025/2/8 10:39
 */
@SpringBootApplication(scanBasePackages = {"com.ran.shard"})
@MapperScan(basePackages = "com.ran.shard.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MainApplication.class);
        application.run(args);
    }
}
