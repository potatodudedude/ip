package dodo.command;

import static dodo.utilities.TimeStringUtility.stringToLd;

import java.time.LocalDate;

import dodo.Storage;
import dodo.UI;
import dodo.task.TaskList;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;

public class DueCommand extends Command {
    private String[] contents;

    public DueCommand(String[] contents) {
        super(false);
        this.contents = contents;
    }

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
        ui.printTaskList(filteredList);
    }
}
