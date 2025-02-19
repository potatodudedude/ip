package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;

/**
 * Command subclass that implements handling the outermost layer of invalid user commands.
 */
public class InvalidCommand extends Command {
    private int type;

    /**
     * Constructor that marks isExit as false.
     */
    public InvalidCommand(int type) {
        super(false);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    /**
     * Uses type to determine if the command is empty or gibberish.
     * Calls the UI to print the appropriate error message.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        switch(type) {
        case 0: // No command line
            return ui.getEmptyCommandMessage();
        case 1: // Unrecognised command
            return ui.getInvalidCommandMessage();
        default:
            return ui.getEmptyCommandMessage();
        }

    }
}
