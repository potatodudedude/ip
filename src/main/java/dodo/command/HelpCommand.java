package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;


/**
 * Command subclass that implements helping.
 */
public class HelpCommand extends Command {

    /**
     * Calls UI to print the help list.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.getHelpMessage();
    }
}
