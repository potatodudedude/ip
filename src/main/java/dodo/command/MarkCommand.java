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
    private int type;
    private String[] contents;

    /**
     * Constructor that marks isExit as false.
     */
    public MarkCommand(int type, String[] contents) {
        super(false);
        this.type = type;
        this.contents = contents;
    }

    public int getType() {
        return type;
    }

    /**
     * Checks for command line validity, then marks/unmarks the appropriate task
     * Uses type to check mark/unmark.
     * Parsed command line is given by the contents array.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        assert (type == 0 || type == 1);
        if (type == 0) { // mark
            int targetNo;
            try {
                DodoCheck.markCommandCheck(contents);
                targetNo = DodoCheck.taskNumberParse(contents[1]);
                DodoCheck.validTaskNumberCheck(targetNo, tasks);
                DodoCheck.redundantMarkCheck(targetNo, true, tasks);
            } catch (DodoException ex) {
                return ui.addPrintErrorPrefix(ex.getMessage());
            }
            targetNo = Integer.parseInt(contents[1]) - 1;
            Task target = tasks.get(targetNo);
            target.setDone();
            storage.update(tasks);
            return ui.getUpdateMarkMessage(target, true);
        } else if (type == 1) { // unmark
            int targetNo;
            try {
                DodoCheck.markCommandCheck(contents);
                targetNo = DodoCheck.taskNumberParse(contents[1]);
                DodoCheck.validTaskNumberCheck(targetNo, tasks);
                DodoCheck.redundantMarkCheck(targetNo, false, tasks);
            } catch (DodoException ex) {
                return ui.addPrintErrorPrefix(ex.getMessage());
            }
            targetNo = Integer.parseInt(contents[1]) - 1;
            Task target = tasks.get(targetNo);
            target.setUndone();
            storage.update(tasks);
            return ui.getUpdateMarkMessage(target, false);
        }
        return ui.getInvalidCommandMessage();
    }
}
