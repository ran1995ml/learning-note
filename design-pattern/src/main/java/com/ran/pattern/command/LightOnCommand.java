package com.ran.pattern.command;

/**
 * LightCommand
 *
 * @author rwei
 * @since 2024/8/13 22:42
 */
public class LightCommand implements Command {
    Light light;

    public LightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
