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
}
