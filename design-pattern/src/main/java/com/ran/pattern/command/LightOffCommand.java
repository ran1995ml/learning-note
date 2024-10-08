package com.ran.pattern.command;

/**
 * LightOffCommand
 *
 * @author rwei
 * @since 2024/8/15 11:58
 */
public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
