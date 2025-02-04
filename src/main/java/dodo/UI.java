package dodo;

import dodo.task.Task;
import dodo.task.TaskList;

import java.time.LocalDate;
import java.util.Scanner;

import static dodo.utilities.TimeStringUtility.PRESENTATION_DF;

/**
 * Class for printing to the user and receiving inputs.
 */
public class UI {
    private int dodoheadCount;
    Scanner uiScanner;
    public UI() {
        this.dodoheadCount = 0;
        this.uiScanner = new Scanner(System.in);
    }

    /**
     * Prints intro text and art.
     */
    public void intro() {
        String logo = " _____   ____  _____   ____  \n"
                + "|  __ \\ / __ \\|  __ \\ / __ \\ \n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |__| | |__| | |__| | |__| |\n"
                + "|_____/ \\____/|_____/ \\____/ \n";
        System.out.println(logo);
        System.out.println("Dododo do dododo do dododo.\n" + "What is your command?");
    }

    /**
     * Returns the next user input line.
     */
    public String readInput() {
        String temp = uiScanner.nextLine();
        return temp;
    }

    /**
     * Checks if dodoheadCount is high enough, if so the user is prompted to use the help command.
     * Resets count after.
     */
    public void dodoCheck() {
        if (dodoheadCount > 3) {
            dodoheadCount = 0;
            System.out.println("You seem to be struggling. Type 'help' if you need to see the command list.");
        }
    }

    /**
     * Increases dodoheadCount.
     */
    public void dodoHead() {
        dodoheadCount++;
    }

    /**
     * Prints goodbye message.
     */
    public void bye() {
        System.out.println("K bye bye :)");
    }

    /**
     * Prints that there are no tasks.
     */
    public void noTask() {
        System.out.println("You have no tasks. Yay!");
    }

    /**
     * Prints header text before list.
     */
    public void taskHeader() {
        System.out.println("Here are your tasks:");
    }

    /**
     * Prints text for no command.
     */
    public void emptyCommand() {
        System.out.println("You've gotta enter a command first dodo head!");
    }

    /**
     * Prints text for invalid command.
     */
    public void invalidCommand() {
        System.out.println("Huh?");
    }

    /**
     * Prints text after adding tasks displaying the task added and the new total.
     */
    public void updateTaskList(TaskList tasks, Task newTask) {
        System.out.println("Added this to your list:\n" + newTask.toString() +
                "\nYou now have " + tasks.size() + " task(s).");
    }

    /**
     * Prints text after marking/unmarking tasks displaying the task marked/unmarked.
     */
    public void updateMark(Task task, boolean isMark) {
        if (isMark) {
            System.out.println("Marked as done:\n" + task.toString());
        } else {
            System.out.println("Marked as undone:\n" + task.toString());
        }
    }

    /**
     * Prints text after deleting tasks displaying the task deleted and the new total.
     */
    public void updateDelete(TaskList tasks, Task target) {
        System.out.println("The following task has been destroyed:\n" + target.toString() +
                "\nYou now have " + tasks.size() + " task(s).");
    }

    /**
     * Prints the text preceding the list of tasks due on the input date.
     */
    public void updateDue(LocalDate date) {
        System.out.println("Here are the tasks due on " +
                date.format(PRESENTATION_DF) + ":");
    }

    /**
     * Gets TaskList class to print out the input TaskList
     */
    public void printTaskList(TaskList tasks) {
        tasks.taskPrinter();
    }

    /**
     * Prints out a prefix before a DodoException message.
     */
    public void printError(String message) {
        System.out.println("Uh oh! " + message);
    }

    /**
     * Prints out a help list for commands.
     */
    public void report() {
        System.out.println("You got it boss! Here you go:\n" +
                "list -> lists all current tasks and their numbering\n" +
                "todo 'name' -> adds a task called 'name'\n" +
                "commands that need 'time' must be in the yyyy-mm-dd hh:ss format" +
                "deadline 'name' /by 'time' -> adds a task called 'name' with deadline of 'time'\n" +
                "event 'name' /from 'start' to 'end' -> adds a task called 'name' with timeframe from " +
                "'start' to 'end'\n" +
                "mark 'task number' -> marks corresponding task as done\n" +
                "unmark 'task number' -> marks corresponding task as undone\n" +
                "delete 'task number' -> removes corresponding task" +
                "bye -> dododo");
    }
}
