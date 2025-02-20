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
    private String[] contents;

    /**
     * Constructor that marks isExit as false.
     */
    public FindCommand(String[] contents) {
        super(false);
        this.contents = contents;
    }

    /**
     * Checks the parsed command line contents for validity, then calls TaskList to filter the tasks by key phrase.
     * Calls UI to print the filtered list.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            DodoCheck.findCommandCheck(contents);
        } catch (DodoException ex) {
            ui.addPrintErrorPrefix(ex.getMessage());
        }
        TaskList filteredList = tasks.findByDescription(contents[1]);
        String result;
        result = ui.addUpdateFindPrefix(contents[1]) + "\n";
        if (filteredList.isEmpty()) {
            result += ui.getEmptyListMessage();
        } else {
            result += ui.getTaskListMessage(filteredList);
        }
        return result;
    }
}
