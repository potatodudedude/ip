package dodo.command;

import static dodo.utilities.TimeStringUtility.stringToLdt;

import java.time.LocalDateTime;

import dodo.Storage;
import dodo.UI;
import dodo.task.Deadline;
import dodo.task.Event;
import dodo.task.Task;
import dodo.task.TaskList;
import dodo.task.Todo;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;

/**
 * Command subclass that implements adding Todo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {
    private int type;

    private String[] contents;

    /**
     * Constructor that marks isExit as false.
     */
    public AddCommand(int type, String[] contents) {
        super(false);
        this.type = type;
        this.contents = contents;
    }

    public int getType() {
        return type;
    }

    public String[] getContents() {
        return contents;
    }

    /**
     * Determines type of Task, then splits the contents String to form the appropriate descriptions
     * Calls the UI for appropriate prints.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        assert (0 <= type && type >= 2);
        try {
            DodoCheck.addCommandCheck(contents);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }
        String timeDescriptor = contents[1];
        Task newTask;
        switch (type) {
        case 0: { // Todo
            newTask = new Todo(contents[1]);
            tasks.addTask(newTask);
            storage.update(tasks);
            return ui.getUpdateTaskListMessage(tasks, newTask);
        }
        case 1: { // Deadline
            String[] details = timeDescriptor.split(" /by ", 2);
            LocalDateTime time;
            try {
                DodoCheck.deadlineCommandCheck(details);
                time = stringToLdt(details[1]);
                DodoCheck.expiredTaskCheck(time);
            } catch (DodoException ex) {
                return ui.addPrintErrorPrefix(ex.getMessage());
            }
            newTask = new Deadline(details[0], time);
            tasks.addTask(newTask);
            storage.update(tasks);
            return ui.getUpdateTaskListMessage(tasks, newTask);
        }
        case 2: { // Event
            String[] details = timeDescriptor.split(" \\/from | \\/to ", 3);
            LocalDateTime start;
            LocalDateTime end;
            try {
                DodoCheck.eventCommandCheck(details);
                start = stringToLdt(details[1]);
                end = stringToLdt(details[2]);
                DodoCheck.validEventTimeCheck(start, end);
                DodoCheck.expiredTaskCheck(end);
            } catch (DodoException ex) {
                return ui.addPrintErrorPrefix(ex.getMessage());
            }
            newTask = new Event(details[0], start, end);
            tasks.addTask(newTask);
            storage.update(tasks);
            return ui.getUpdateTaskListMessage(tasks, newTask);
        }
        default:
            return ui.getInvalidCommandMessage();
        }
    }
}
