package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;

/**
 * Command subclass that implements listing tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    /**
     * Calls UI to print out tasks.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
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
