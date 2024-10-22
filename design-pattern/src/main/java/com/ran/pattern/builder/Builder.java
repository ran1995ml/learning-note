package com.ran.pattern.builder;

/**
 * Builder
 *
 * @author rwei
 * @since 2024/9/16 22:57
 */
public abstract class Builder {
    public abstract Builder buildBoard(String board);

    public abstract Builder buildDisplay(String display);

    public abstract Builder buildOs();

    public abstract Computer build();
}
