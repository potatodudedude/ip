package dodo.command;

import static dodo.utilities.TimeStringUtility.stringToLd;

import java.time.LocalDate;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;
import dodo.utilities.TextColourPair;

/**
 * Command subclass that implements finding tasks by matching expiry dates.
 */
public class DueCommand extends Command {
    private String[] timeString;

    /**
     * Constructor that sets user command line.
     */
    public DueCommand(String[] timeString) {
        this.timeString = timeString;
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
    public TextColourPair execute(TaskList tasks, UI ui, Storage storage) {
        LocalDate date;
        try {
            DodoCheck.checkDueCommand(timeString);
            date = stringToLd(timeString[1]);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }

        TaskList filteredList = tasks.findByDate(date);
        return ui.getDueMessage(filteredList, date);
    }
}
