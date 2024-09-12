<<<<<<< HEAD
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
=======
package com.ran.pattern.command;/** 
 * LightOffCommand
 * 
 * @author rwei
 * @since 2024/8/15 11:58
 */public class LightOffCommand {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
