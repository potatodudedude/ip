package dodo.command;

import dodo.Storage;
import dodo.UI;
import dodo.task.Task;
import dodo.task.TaskList;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;

/**
 * Command subclass that implements marking/unmarking tasks.
 */
public class MarkCommand extends Command {
    private boolean isMark;
    private String[] taskNumberString;

    /**
     * Constructor that marks isExit as false.
     */
    public MarkCommand(boolean isMark, String[] taskNumberString) {
        this.isMark = isMark;
        this.taskNumberString = taskNumberString;
    }

    public boolean getIsMark() {
        return isMark;
    }

    /**
     * Checks for command line validity, then marks/unmarks the appropriate task and returns the marked message.
     * Uses type to check mark/unmark.
     * Parsed command line is given by the contents array.
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
            DodoCheck.checkMarkCommand(taskNumberString);
            targetNo = DodoCheck.parseTaskNumber(taskNumberString[1]);
            DodoCheck.checkValidTaskNumber(targetNo, tasks);
            if (isMark) {
                DodoCheck.checkRedundantMark(targetNo, true, tasks);
            } else {
                DodoCheck.checkRedundantMark(targetNo, false, tasks);
            }
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }
        targetNo = Integer.parseInt(taskNumberString[1]) - 1;
        Task target = tasks.get(targetNo);
        if (isMark) {
            target.setDone();
            storage.update(tasks);
            return ui.getUpdateMarkMessage(target, true);
        } else {
            target.setUndone();
            storage.update(tasks);
            return ui.getUpdateMarkMessage(target, false);
        }
    }
}
