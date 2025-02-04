package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;


public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }

}
