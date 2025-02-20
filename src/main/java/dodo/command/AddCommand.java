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

    /**
     * Task type integer that allows for easy passing of information from parsing.
     * 0 = Todo
     * 1 = Deadline
     * 2 = Event
     */
    private int taskType;

    private String[] taskDescriptions;

    /**
     * Constructor that marks isExit as false.
     */
    public AddCommand(int taskType, String[] taskDescriptions) {
        super(false);
        this.taskType = taskType;
        this.taskDescriptions = taskDescriptions;
    }

    public int getTaskType() {
        return this.getTaskType();
    }

    public String[] getTaskDescriptions() {
        return this.taskDescriptions;
    }

    /**
     * Common code used for adding a task to a taskList, storage, and returning the added task message
     *
     * @param newTask
     * @param ui UI used to return message needed
     * @param storage
     * @return String message for successful add command
     */
    private String addTask(Task newTask, TaskList tasks, UI ui, Storage storage) {
        tasks.addTask(newTask);
        storage.update(tasks);
        return ui.getUpdateTaskListMessage(tasks, newTask);
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
        try {
            DodoCheck.addCommandCheck(taskDescriptions);
        } catch (DodoException ex) {
            return ui.addPrintErrorPrefix(ex.getMessage());
        }
        String timeDescriptor = taskDescriptions[1];
        Task newTask;
        switch (taskType) {
        case 0: { // Todo
            newTask = new Todo(taskDescriptions[1]);
            return addTask(newTask, tasks, ui, storage);
        }
        case 1: { // Deadline
            String[] details = timeDescriptor.split(" /by ", 2);
            LocalDateTime deadline;
            try {
                DodoCheck.deadlineCommandCheck(details);
                deadline = stringToLdt(details[1]);
                DodoCheck.expiredTaskCheck(deadline);
            } catch (DodoException ex) {
                return ui.addPrintErrorPrefix(ex.getMessage());
            }
            newTask = new Deadline(details[0], deadline);
            return addTask(newTask, tasks, ui, storage);
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
            return addTask(newTask, tasks, ui, storage);
        }
        default:
            return ui.getInvalidCommandMessage();
        }
    }
}
