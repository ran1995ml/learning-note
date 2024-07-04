package com.ran.jackson.subtype;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Red
 *
 * @author rwei
 * @since 2024/6/18 14:21
 */
public class Red implements Type {
    @JsonProperty
    @Override
    public String getType() {
        return "red";
    }
}
