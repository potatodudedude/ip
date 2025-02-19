package dodo;

import static dodo.utilities.TimeStringUtility.PRESENTATION_DF;

import java.time.LocalDate;

import dodo.task.Task;
import dodo.task.TaskList;

/**
 * Class for messages to the user.
 */
public class UI {
    private int dodoheadCount;

    /**
     * Constructor initialising dodoheadCount to 0.
     */
    public UI() {
        this.dodoheadCount = 0;
    }

    /**
     * Returns intro text and art.
     */
    public String getIntroMessage() {
        String logo = " _____   ____  _____   ____  \n"
                + "|  __ \\ / __ \\|  __ \\ / __ \\ \n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |__| | |__| | |__| | |__| |\n"
                + "|_____/ \\____/|_____/ \\____/ \n";
        return (logo + "\n" + "Dododo do dododo do dododo.\n" + "What is your command?");
    }

    /**
     * Upon receiving an error message, checks if user has had repeated errors, if so adds help prompt to end of line.
     * repeated error count "dodoheadCount" is reset if help prompt is made.
     */
    private String dodoCheck(String errorLine) {
        if (dodoheadCount > 3) {
            dodoheadCount = 0;
            return (errorLine + "\nYou seem to be struggling. Type 'help' if you need to see the command list.");
        }
        return errorLine;
    }

    /**
     * Increases dodoheadCount.
     */
    private void increaseDodohead() {
        dodoheadCount++;
    }

    /**
     * Returns goodbye message.
     */
    public String getByeMessage() {
        return ("K bye bye :)");
    }

    /**
     * Returns that there are no tasks.
     */
    public String getNoTaskMessage() {
        return ("You have no tasks. Yay!");
    }

    /**
     * Returns header text before list.
     */
    public String getTaskHeaderMessage() {
        return ("Here are your tasks:");
    }

    /**
     * Returns text for no command.
     */
    public String getEmptyCommandMessage() {
        return ("You've gotta enter a command first dodo head!");
    }

    /**
     * Returns text for invalid command.
     */
    public String getInvalidCommandMessage() {
        return ("Huh?");
    }

    /**
     * Returns text after adding tasks displaying the task added and the new total.
     */
    public String getUpdateTaskListMessage(TaskList tasks, Task newTask) {
        return ("Added this to your list:\n" + newTask.toString()
                + "\nYou now have " + tasks.size() + " task(s).");
    }

    /**
     * Returns text after marking/unmarking tasks displaying the task marked/unmarked.
     */
    public String getUpdateMarkMessage(Task task, boolean isMark) {
        if (isMark) {
            return ("Marked as done:\n" + task.toString());
        } else {
            return ("Marked as undone:\n" + task.toString());
        }
    }

    /**
     * Returns text after deleting tasks displaying the task deleted and the new total.
     */
    public String getUpdateDeleteMessage(TaskList tasks, Task target) {
        return ("The following task has been destroyed:\n" + target.toString()
                + "\nYou now have " + tasks.size() + " task(s).");
    }

    /**
     * Returns the text preceding the list of tasks due on the input date.
     */
    public String addUpdateDuePrefix(LocalDate date) {
        return ("Here are the tasks due on "
                + date.format(PRESENTATION_DF) + ":");
    }

    /**
     * Returns the text preceding the list of tasks matching the find command.
     */
    public String addUpdateFindPrefix(String line) {
        return ("Here are the tasks matching your description of: " + line);
    }

    /**
     * Gets TaskList class to return the input TaskList
     */
    public String getTaskListMessage(TaskList tasks) {
        return tasks.taskPrinter();
    }

    /**
     * Returns out a prefix before a DodoException message.
     */
    public String addPrintErrorPrefix(String message) {
        increaseDodohead();
        return dodoCheck("Uh oh! " + message);
    }

    /**
     * Returns out message to convey a list is empty.
     */
    public String getEmptyListMessage() {
        increaseDodohead();
        return dodoCheck("Looks like there were none found. :v");
    }

    /**
     * Returns out a help list for commands.
     */
    public String getHelpMessage() {
        return ("You got it boss! Here you go:\n"
                + "list -> lists all current tasks and their numbering\n"
                + "todo 'name' -> adds a task called 'name'\n"
                + "commands that need 'time' must be in the yyyy-mm-dd hh:ss format"
                + "deadline 'name' /by 'time' -> adds a task called 'name' with deadline of 'time'\n"
                + "event 'name' /from 'start' to 'end' -> adds a task called 'name' with timeframe from "
                + "'start' to 'end'\n"
                + "mark 'task number' -> marks corresponding task as done\n"
                + "unmark 'task number' -> marks corresponding task as undone\n"
                + "delete 'task number' -> removes corresponding task"
                + "due 'yyyy-mm-dd' -> returns a list with the provided due date"
                + "find 'description' -> returns a list with tasks that have the description in their name"
                + "bye -> dododo");
    }
}
