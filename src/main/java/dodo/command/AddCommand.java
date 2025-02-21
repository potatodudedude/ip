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
import dodo.utilities.TextColourPair;

/**
 * Command subclass that implements adding Todo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {

    /**
     * Task type integer that allows for easy passing of information from parsing.
     * 0 = Todo
     * 1 = Deadline
     * 2 = Event
     */
    private int taskType;

    private String[] taskDescriptions;

    /**
     * Constructor that sets task type and user command line.
     */
    public AddCommand(int taskType, String[] taskDescriptions) {
        this.taskType = taskType;
        this.taskDescriptions = taskDescriptions;
    }

    public int getTaskType() {
        return this.taskType;
    }

    public String[] getTaskDescriptions() {
        return this.taskDescriptions;
    }

    /**
     * Adds a task to a taskList, storage, and returns the added task message
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    private TextColourPair addTask(Task newTask, TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(newTask);
        storage.updateTaskListFromStorage(tasks);
        return ui.getUpdateTaskListMessage(tasks, newTask);
    }

    /**
     * Adds Todo type task to taskList and storage and returns message for user
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    private TextColourPair addTodo(TaskList tasks, UI ui, Storage storage) {
        Task newTask = new Todo(taskDescriptions[1]);
        return addTask(newTask, tasks, ui, storage);
    }

    /**
     * Checks for validity, then adds Deadline type task to taskList and storage and returns message for user
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    private TextColourPair addDeadline(TaskList tasks, UI ui, Storage storage) {
        String[] details = taskDescriptions[1].split(" /by ", 2);
        LocalDateTime deadline;

        try {
            DodoCheck.checkDeadlineCommand(details);
            deadline = stringToLdt(details[1]);
            DodoCheck.checkExpiredTask(deadline);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }
        Task newTask = new Deadline(details[0], deadline);
        return addTask(newTask, tasks, ui, storage);
    }

    /**
     * Checks for validity, then adds event type task to taskList and storage and returns message for user
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    private TextColourPair addEvent(TaskList tasks, UI ui, Storage storage) {
        String[] details = taskDescriptions[1].split(" \\/from | \\/to ", 3);
        LocalDateTime start;
        LocalDateTime end;

        try {
            DodoCheck.checkEventCommand(details);
            start = stringToLdt(details[1]);
            end = stringToLdt(details[2]);
            DodoCheck.checkValidEventTime(start, end);
            DodoCheck.checkExpiredTask(end);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }
        Task newTask = new Event(details[0], start, end);
        return addTask(newTask, tasks, ui, storage);
    }

    /**
     * Determines type of Task, then splits taskDescriptions to form the appropriate descriptions
     * Calls the UI for appropriate message returns.
     *
     * @param tasks TaskList for storing tasks.
     * @param ui UI for printing messages.
     * @param storage Storage to save data to.
     * @return String of message to send to user.
     */
    @Override
    public TextColourPair execute(TaskList tasks, UI ui, Storage storage) {
        try {
            DodoCheck.checkAddCommand(taskDescriptions);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }

        switch (taskType) {
        case 0: { // Todo
            return addTodo(tasks, ui, storage);
        }
        case 1: { // Deadline
            return addDeadline(tasks, ui, storage);
        }
        case 2: { // Event
            return addEvent(tasks, ui, storage);
        }
        default:
            return ui.getInvalidCommandMessage();
        }
    }
}
