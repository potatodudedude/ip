package dodo.command;

import java.time.LocalDate;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;
import dodo.utilities.TextColourPair;


/**
 * Command subclass that implements reminders for tasks due today
 */
public class ReminderCommand extends Command {

    /**
     * Checks and gives returns reminder message for tasks due today, if any.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    @Override
    public TextColourPair execute(TaskList tasks, UI ui, Storage storage) {
        LocalDate date = LocalDate.now();

        TaskList filteredList = tasks.findByDate(date);
        return ui.getReminderMessage(filteredList);
    }
}
