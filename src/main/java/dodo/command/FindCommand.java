package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;
import dodo.utilities.TextColourPair;

/**
 * Command subclass that allows searching for tasks by description.
 */
public class FindCommand extends Command {
    private String[] searchString;

    /**
     * Constructor that sets user command line.
     */
    public FindCommand(String[] searchString) {
        this.searchString = searchString;
    }

    /**
     * Checks the parsed command line contents for validity, then calls TaskList to filter the tasks by key phrase.
     * Calls UI to print the filtered list.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    @Override
    public TextColourPair execute(TaskList tasks, UI ui, Storage storage) {
        try {
            DodoCheck.checkFindCommand(searchString);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }

        String description = searchString[1];
        TaskList filteredList = tasks.findByDescription(description);
        return ui.getFindMessage(filteredList, description);
    }
}
