package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;


/**
 * Command subclass that implements exiting.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super("ByeCommand");
    }
    /**
     * Calls the UI to return bye message.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.getByeMessage();
    }
}
