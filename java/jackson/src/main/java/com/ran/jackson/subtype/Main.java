package com.ran.jackson.subtype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Main
 *
 * @author rwei
 * @since 2024/6/18 14:34
 */
public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Blue blue = new Blue();
        System.out.println(objectMapper.writeValueAsString(blue));
        Red red = new Red();
        System.out.println(objectMapper.writeValueAsString(red));
    }
}
