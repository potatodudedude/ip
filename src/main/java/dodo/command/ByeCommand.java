package dodo.command;

import dodo.Storage;
import dodo.task.TaskList;
import dodo.UI;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.bye();
    }
}
