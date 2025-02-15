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
     * Constructor that marks isExit as false.
     */
    public DueCommand(String[] contents) {
        super(false);
        this.contents = contents;
    }

    /**
     * Checks the parsed command line contents for validity, then calls TaskList to filter the tasks by date.
     * Calls UI to print the filtered list.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        LocalDate date;
        try {
            DodoCheck.dueCommandCheck(contents);
            date = stringToLd(contents[1]);
        } catch (DodoException ex) {
            ui.printError(ex.getMessage());
            return;
        }
        TaskList filteredList = tasks.findByDate(date);
        ui.updateDue(date);
        if (filteredList.isEmpty()) {
            ui.printEmptyList();
        } else {
            ui.printTaskList(filteredList);
        }
    }
}
