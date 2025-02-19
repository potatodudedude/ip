package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.Task;
import dodo.task.TaskList;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;

/**
 * Command subclass that implements deleting tasks.
 */
public class DeleteCommand extends Command {
    private String[] contents;

    /**
     * Constructor that marks isExit as false.
     */
    public DeleteCommand(String[] contents) {
        super(false);
        this.contents = contents;
    }

    /**
     * Checks that the parsed command line contents is valid, then retrieves the task number and removes it from tasks.
     * Calls the UI to show deleted task.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        int targetNo;
        try {
            DodoCheck.deleteCommandCheck(contents);
            targetNo = DodoCheck.taskNumberParse(contents[1]);
            DodoCheck.validTaskNumberCheck(targetNo, tasks);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }
        targetNo = Integer.parseInt(contents[1]) - 1;
        Task target = tasks.get(targetNo);
        tasks.removeTask(targetNo);
        storage.update(tasks);
        return ui.getUpdateDeleteMessage(tasks, target);
    }
}
