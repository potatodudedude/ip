package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;


public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.bye();
    }
}
