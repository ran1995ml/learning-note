package com.ran.pattern.command;

/**
<<<<<<< HEAD
 * LightOnCommand
=======
 * LightCommand
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
 *
 * @author rwei
 * @since 2024/8/13 22:42
 */
<<<<<<< HEAD
public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
=======
public class LightCommand implements Command {
    Light light;

    public LightCommand(Light light) {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
<<<<<<< HEAD

    @Override
    public void undo() {
        light.off();
    }
=======
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
