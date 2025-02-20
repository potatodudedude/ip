package dodo.command;

import static dodo.utilities.TimeStringUtility.stringToLd;

import java.time.LocalDate;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;

/**
 * Command subclass that implements finding tasks by matching expiry dates.
 */
public class DueCommand extends Command {
    private String[] contents;

    /**
     * Constructor
     */
    public DueCommand(String[] contents) {
        this.contents = contents;
    }

    /**
     * Checks the parsed command line contents for validity, then calls TaskList to filter the tasks by date.
     * Calls UI to return the filtered list message.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        LocalDate date;
        try {
            DodoCheck.checkDueCommand(contents);
            date = stringToLd(contents[1]);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }
        TaskList filteredList = tasks.findByDate(date);
        String result;
        result = ui.addUpdateDuePrefix(date) + "\n";
        if (filteredList.isEmpty()) {
            result += ui.getEmptyListMessage();
        } else {
            result += ui.getTaskListMessage(filteredList);
        }
        return result;
    }
}
