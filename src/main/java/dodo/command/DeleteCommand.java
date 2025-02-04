package dodo.command;

import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;
import dodo.Storage;
import dodo.UI;
import dodo.task.Task;
import dodo.task.TaskList;

public class DeleteCommand extends Command {
    private String[] contents;
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
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int targetNo;
        try {
            DodoCheck.deleteCommandCheck(contents);
            targetNo = DodoCheck.taskNumberParse(contents[1]);
            DodoCheck.validTaskNumberCheck(targetNo, tasks);
        } catch (DodoException ex) {
            ui.printError(ex.getMessage());
            return;
        }
        targetNo = Integer.parseInt(contents[1]) - 1;
        Task target = tasks.get(targetNo);
        tasks.removeTask(targetNo);
        storage.update(tasks);
        ui.updateDelete(tasks, target);
    }
}
