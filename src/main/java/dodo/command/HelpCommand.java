package dodo.command;

import dodo.Storage;
import dodo.task.TaskList;
import dodo.UI;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.report();
    }
}
