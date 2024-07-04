package com.ran.jackson.subtype;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Blue
 *
 * @author rwei
 * @since 2024/6/18 14:22
 */
public class Blue implements Type {
    @JsonProperty
    @Override
    public String getType() {
        return "blue";
    }
}
