package dodo.command;

import dodo.Storage;
import dodo.task.TaskList;
import dodo.UI;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command's specific purpose.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }

}
