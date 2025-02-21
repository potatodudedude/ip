package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;
import dodo.utilities.TextColourPair;

/**
 * Command subclass that implements handling the outermost layer of invalid user commands.
 */
public class InvalidCommand extends Command {
    private int invalidType;

    /**
     * Constructor that sets type of invalid command.
     */
    public InvalidCommand(int type) {
        this.invalidType = type;
    }

    public int getType() {
        return invalidType;
    }

    /**
     * Uses type to determine if the command is empty or gibberish.
     * Calls the UI to print the appropriate error message.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    @Override
    public TextColourPair execute(TaskList tasks, UI ui, Storage storage) {
        switch(invalidType) {
        case 0: // No command line
            return ui.getEmptyCommandMessage();
        case 1: // Unrecognised command
            return ui.getInvalidCommandMessage();
        default:
            return ui.getEmptyCommandMessage();
        }

    }
}
