package com.ran.pattern.command;

/**
 * LightOnCommand
 *
 * @author rwei
 * @since 2024/8/13 22:42
 */
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
