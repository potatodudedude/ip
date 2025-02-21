package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;

/**
 * Command subclass that allows searching for tasks by description.
 */
public class FindCommand extends Command {
    private String[] searchString;

    /**
     * Constructor that marks isExit as false.
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
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            DodoCheck.checkFindCommand(searchString);
        } catch (DodoException ex) {
            ui.addPrintErrorPrefix(ex.getMessage());
        }

        TaskList filteredList = tasks.findByDescription(searchString[1]);
        String result;
        result = ui.getUpdateFindHeader(searchString[1]);
        if (filteredList.isEmpty()) {
            result += ui.getEmptyListMessage();
        } else {
            result += ui.getTaskListMessage(filteredList);
        }
        return result;
    }
}
