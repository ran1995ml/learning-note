<<<<<<< HEAD
package com.ran.pattern.command;

/**
 * RemoteControl
 * 调用者对象
 *
 * @author rwei
 * @since 2024/8/15 11:45
 */
public class RemoteControl {
    Command[] onCommands;

    Command[] offCommands;

    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pressOn(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void pressOff(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undo() {
        undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            String slotDescription = String.format("[slot %s: %s %s]\n", i, onCommands[i].getClass().getName(), offCommands[i].getClass().getName());
            sb.append(slotDescription);
        }
        return sb.toString();
    }
=======
package com.ran.pattern.command;/** 
 * RemoteControl
 * 
 * @author rwei
 * @since 2024/8/15 11:45
 */public class RemoteControl {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
