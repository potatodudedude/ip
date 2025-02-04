package dodo;

import dodo.task.Task;
import dodo.task.TaskList;

import java.time.LocalDate;
import java.util.Scanner;

import static dodo.utilities.TimeStringUtility.PRESENTATION_DF;

public class UI {
    private int dodoheadCount;
    Scanner uiScanner;
    public UI() {
        this.dodoheadCount = 0;
        this.uiScanner = new Scanner(System.in);
    }
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

    public String readInput() {
        String temp = uiScanner.nextLine();
        return temp;
    }

    public void dodoCheck() {
        if (dodoheadCount > 3) {
            dodoheadCount = 0;
            System.out.println("You seem to be struggling. Type 'help' if you need to see the command list.");
        }
    }
    public void dodoHead() {
        dodoheadCount++;
    }

    public void bye() {
        System.out.println("K bye bye :)");
    }

    public void noTask() {
        System.out.println("You have no tasks. Yay!");
    }

    public void taskHeader() {
        System.out.println("Here are your tasks:");
    }

    public void emptyCommand() {
        System.out.println("You've gotta enter a command first dodo head!");
    }

    public void invalidCommand() {
        System.out.println("Huh?");
    }

    public void updateTaskList(TaskList tasks, Task newTask) {
        System.out.println("Added this to your list:\n" + newTask.toString() +
                "\nYou now have " + tasks.size() + " task(s).");
    }

    public void updateMark(Task task, boolean isMark) {
        if (isMark) {
            System.out.println("Marked as done:\n" + task.toString());
        } else {
            System.out.println("Marked as undone:\n" + task.toString());
        }
    }

    public void updateDelete(TaskList tasks, Task target) {
        System.out.println("The following task has been destroyed:\n" + target.toString() +
                "\nYou now have " + tasks.size() + " task(s).");
    }

    public void updateDue(LocalDate date) {
        System.out.println("Here are the tasks due on " +
                date.format(PRESENTATION_DF) + ":");
    }

    public void updateFind(String line) {
        System.out.println("Here are the tasks matching your description of: "
                + line + ":");
    }

    public void printTaskList(TaskList tasks) {
        tasks.taskPrinter();
    }

    public void printError(String message) {
        System.out.println("Uh oh! " + message);
    }
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
