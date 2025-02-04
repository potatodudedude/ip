package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;


public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.report();
    }
}
