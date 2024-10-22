package com.ran.pattern.prototype;

/**
 * Shape
 *
 * @author rwei
 * @since 2024/9/26 11:32
 */
public abstract class Shape implements Cloneable {
    private String id;

    protected String type;

    public abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
