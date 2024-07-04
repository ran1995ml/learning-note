package com.ran.jackson.subtype;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Type
 *
 * @author rwei
 * @since 2024/6/18 14:19
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "extraType")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(name = "redType", value = Red.class),
        @JsonSubTypes.Type(name = "blueType", value = Blue.class)
})
public interface Type {
    String getType();
}
