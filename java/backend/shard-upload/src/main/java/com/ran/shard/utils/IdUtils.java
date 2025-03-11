package com.ran.shard.utils;

import java.util.UUID;

/**
 * IdUtils
 *
 * @author rwei
 * @since 2025/2/8 14:06
 */
public class IdUtils {
    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
