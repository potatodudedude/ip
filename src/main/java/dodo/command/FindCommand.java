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

    public FindCommand(String[] contents) {
        super(false);
        this.contents = contents;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        try {
            DodoCheck.findCommandCheck(contents);
        } catch (DodoException ex) {
            ui.printError(ex.getMessage());
        }
        TaskList filteredList = tasks.findByDescription(contents[1]);
        ui.updateFind(contents[1]);
        ui.printTaskList(filteredList);
    }
}
