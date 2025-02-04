package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.noTask();
            return;
        }
        ui.taskHeader();
        ui.printTaskList(tasks);
    }
}
