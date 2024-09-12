<<<<<<< HEAD
package com.ran.pattern.command;

/**
 * DoorOnCommand
 *
 * @author rwei
 * @since 2024/8/15 12:48
 */
public class DoorOnCommand implements Command {
    Door door;

    public DoorOnCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.on();
    }

    @Override
    public void undo() {
        door.off();
    }
=======
package com.ran.pattern.command;/** 
 * DoorOnCommand
 * 
 * @author rwei
 * @since 2024/8/15 12:48
 */public class DoorOnCommand {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
