package com.ran.pattern.command;

/**
 * DoorOffCommand
 *
 * @author rwei
 * @since 2024/8/15 12:51
 */
public class DoorOffCommand implements Command {
    Door door;

    public DoorOffCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.off();
    }

    @Override
    public void undo() {
        door.on();
    }
}
