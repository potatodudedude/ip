package dodo.command;

import dodo.*;
import dodo.task.*;
import dodo.utilities.DodoCheck;
import dodo.utilities.DodoException;

import java.time.LocalDateTime;

import static dodo.utilities.TimeStringUtility.stringToLdt;

/**
 * Command subclass that implements adding Todo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {
    private int type;

    private String contents;
    public AddCommand(int type, String contents) {
        super(false);
        this.type = type;
        this.contents = contents;
    }

    public int getType() {
        return type;
    }

    public String getContents() {
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
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task newTask;
        switch (type) {
        case 0: { // Todo
            newTask = new Todo(contents);
            tasks.addTask(newTask);
            storage.update(tasks);
            ui.updateTaskList(tasks, newTask);
            break;
        }
        case 1: { // Deadline
            String[] details = contents.split(" /by ", 2);
            LocalDateTime time;
            try {
                DodoCheck.deadlineCommandCheck(details);
                time = stringToLdt(details[1]);
                DodoCheck.expiredTaskCheck(time);
            } catch (DodoException ex) {
                ui.printError(ex.getMessage());
                break;
            }
            newTask = new Deadline(details[0], time);
            tasks.addTask(newTask);
            storage.update(tasks);
            ui.updateTaskList(tasks, newTask);
            break;
        }
        case 2: { // Event
            String[] details = contents.split(" \\/from | \\/to ", 3);
            LocalDateTime start;
            LocalDateTime end;
            try {
                DodoCheck.eventCommandCheck(details);
                start = stringToLdt(details[1]);
                end = stringToLdt(details[2]);
                DodoCheck.validEventTimeCheck(start, end);
                DodoCheck.expiredTaskCheck(end);
            } catch (DodoException ex) {
                ui.printError(ex.getMessage());
                break;
            }
            newTask = new Event(details[0], start, end);
            tasks.addTask(newTask);
            storage.update(tasks);
            ui.updateTaskList(tasks, newTask);
            break;
        }
        }
    }
}
