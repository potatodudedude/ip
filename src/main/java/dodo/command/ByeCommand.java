package dodo.command;

import dodo.Storage;
import dodo.task.TaskList;
import dodo.UI;

/**
 * Command subclass that implements exiting.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    /**
     * Calls the UI to say bye.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.bye();
    }
}
