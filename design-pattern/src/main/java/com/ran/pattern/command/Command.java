package com.ran.pattern.command;

/**
 * Command
 * 命令对象
 *
 * @author rwei
 * @since 2024/8/13 22:39
 */
public interface Command {
    void execute();

    void undo();
}
