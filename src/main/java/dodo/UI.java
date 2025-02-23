package dodo;

import static dodo.utilities.TimeStringUtility.PRESENTATION_DF;

import java.time.LocalDate;

import dodo.task.Task;
import dodo.task.TaskList;
import dodo.utilities.TextColourPair;

/**
 * Class for messages to the user.
 */
public class UI {
    public static final String GREEN = "green";
    public static final String YELLOW = "yellow";
    public static final String RED = "red";
    public static final String WHITE = "white";
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
    private TextColourPair checkDodoHead(String errorLine) {
        if (dodoheadCount > 3) {
            dodoheadCount = 0;
            return new TextColourPair(
                    errorLine + "\nYou seem to be struggling. Type 'help' if you need to see the command list.",
                    RED);
        }
        return new TextColourPair(errorLine, RED);
    }

    /**
     * Returns goodbye message.
     */
    public TextColourPair getByeMessage() {
        return new TextColourPair("K bye bye :)", WHITE);
    }

    /**
     * Returns text for no command.
     */
    public TextColourPair getEmptyCommandMessage() {
        dodoheadCount++;
        return checkDodoHead("You've gotta enter a command first dodo head!");
    }

    /**
     * Returns text for invalid command.
     */
    public TextColourPair getInvalidCommandMessage() {
        dodoheadCount++;
        return checkDodoHead("Huh?");
    }

    /**
     * Returns text after adding tasks displaying the task added and the new total.
     */
    public TextColourPair getUpdateTaskListMessage(TaskList tasks, Task newTask) {
        return new TextColourPair("Added this to your list:\n" + newTask.toString()
                + "\nYou now have " + tasks.size() + " task(s).",
                GREEN);
    }

    /**
     * Returns text after marking/unmarking tasks displaying the task marked/unmarked.
     */
    public TextColourPair getUpdateMarkMessage(Task task, boolean isMark) {
        if (isMark) {
            return new TextColourPair("Marked as done:\n" + task.toString(), GREEN);
        } else {
            return new TextColourPair("Marked as undone:\n" + task.toString(), YELLOW);
        }
    }

    /**
     * Returns text after deleting tasks displaying the task deleted and the new total.
     */
    public TextColourPair getUpdateDeleteMessage(TaskList tasks, Task target) {
        return new TextColourPair("The following task has been destroyed:\n" + target.toString()
                + "\nYou now have " + tasks.size() + " task(s).",
                YELLOW);
    }

    /**
     * Returns taskList or message informing of no tasks.
     */
    public TextColourPair getTaskListMessage(TaskList tasks) {
        if (tasks.isEmpty()) {
            return new TextColourPair("You have no tasks. Yay!", GREEN);
        }
        return new TextColourPair("Here are your tasks:\n" + tasks.taskPrinter(), WHITE);
    }

    /**
     * Attaches and returns out a prefix before a DodoException message.
     */
    public TextColourPair addPrintErrorPrefix(String message) {
        dodoheadCount++;
        return checkDodoHead("Uh oh! " + message);
    }

    /**
     * Returns out a message for due commands
     */
    public TextColourPair getDueMessage(TaskList filteredTasks, LocalDate date) {
        if (filteredTasks.isEmpty()) {
            return new TextColourPair("Yippee! No tasks due on: " + date.format(PRESENTATION_DF),
                    GREEN);
        }
        return new TextColourPair("Here are the task(s) due on: " + date.format(PRESENTATION_DF) + "\n"
                + filteredTasks.taskPrinter(),
                WHITE);
    }

    /**
     * Returns out a message for find commands
     */
    public TextColourPair getFindMessage(TaskList filteredTasks, String description) {
        if (filteredTasks.isEmpty()) {
            return new TextColourPair("Whuh oh, no tasks found matching you description of: " + description,
                    YELLOW);
        }
        return new TextColourPair("Here are the task(s) matching your description of: " + description + "\n"
                + filteredTasks.taskPrinter(),
                WHITE);
    }

    /**
     * Returns out a message for reminder tasks
     */
    public TextColourPair getReminderMessage(TaskList filteredTasks) {
        if (filteredTasks.isEmpty()) {
            return new TextColourPair("No tasks due today! Yippie!", GREEN);
        }
        return new TextColourPair("Dododo! These tasks are due or ongoing today!:\n"
                + filteredTasks.taskPrinter(),
                WHITE);
    }

    /**
     * Returns out a help list for commands.
     */
    public TextColourPair getHelpMessage() {
        return new TextColourPair("You got it boss! Here you go:\n"
                + "UPPERCASE words represent your parameters to the command!\n"
                + "list -> lists all current tasks and their numbering\n"
                + "todo NAME -> adds a task called NAME\n"
                + "deadline NAME /by YYYY-MM-DD HH:MM -> adds a task called NAME a deadline\n"
                + "event NAME /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM -> adds a task called NAME that occurs in a "
                + "given timeframe\n"
                + "mark TASK_NUMBER -> marks corresponding task as done\n"
                + "unmark TASK_NUMBER -> marks corresponding task as undone\n"
                + "delete TASK_NUMBER -> removes corresponding task\n"
                + "due YYYY-MM-DD -> returns a list with tasks ongoing or due on a  date\n"
                + "find DESCRIPTION -> returns a list with tasks that have the description in their name\n"
                + "bye or bb -> dododo\n"
                + "go to https://potatodudedude.github.io/ip/ for a more detailed guide!",
                WHITE);
    }
}
