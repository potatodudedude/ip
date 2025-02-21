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
    private String[] taskNumberString;

    /**
     * Constructor that sets user command line.
     */
    public DeleteCommand(String[] taskNumberString) {
        super("DeleteCommand");
        this.taskNumberString = taskNumberString;
    }

    /**
     * Checks that the parsed command line contents is valid, then retrieves the task number and removes it from tasks.
     * Calls the UI to return deleted task message.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        int targetNo;
        try {
            DodoCheck.checkDeleteCommand(taskNumberString);
            targetNo = DodoCheck.parseTaskNumber(taskNumberString[1]);
            DodoCheck.checkValidTaskNumber(targetNo, tasks);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }

        targetNo = Integer.parseInt(taskNumberString[1]) - 1;
        Task target = tasks.get(targetNo);
        tasks.removeTask(targetNo);
        storage.updateTaskListFromStorage(tasks);
        return ui.getUpdateDeleteMessage(tasks, target);
    }
}
