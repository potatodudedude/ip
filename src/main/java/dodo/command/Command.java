package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;


/**
 * Abstract class to represent possible commands.
 * Has abstract method for executing.
 */
public abstract class Command {
    private String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return this.commandType;
    }

    /**
     * Executes the command's specific purpose.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage);

}
