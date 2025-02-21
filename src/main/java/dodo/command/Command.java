package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;
import dodo.utilities.TextColourPair;


/**
 * Abstract class to represent possible commands.
 * Has abstract method for executing.
 */
public abstract class Command {

    /**
     * Executes the command's specific purpose.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    public abstract TextColourPair execute(TaskList tasks, UI ui, Storage storage);

}
