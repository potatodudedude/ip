import java.util.Scanner;
import java.util.ArrayList;

public class Dodo {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void markCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2)  {
            throw new DodoException("Mark/Unmark commands needs to be followed by single task number.\n" +
                    "e.g. mark 2");
        }
    }

    public static void deleteCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2)  {
            throw new DodoException("Delete commands needs to be followed by single task number.\n" +
                    "e.g. delete 2");
        }
    }


    public static void deadlineCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2)  {
            throw new DodoException("Deadline commands needs to be structured as follows:\n" +
                    "deadline 'name' /by 'time'");
        }
    }

    public static void eventCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 3)  {
            throw new DodoException("Event commands needs to be structured as follows:\n" +
                    "event 'name' /from 'start time' /to 'end time'");
        }
    }

    public static void validTaskNumber(int i) throws DodoException {
        if (i > tasks.size() || i < 1) {
            throw new DodoException("Task number " + i + " doesn't exist dodohead!");
        }
    }

    public static void redundantMark(int targetNo, boolean done) throws DodoException {
        Task target = tasks.get(targetNo - 1);
        if (done) {
            if (target.getMark()) {
                throw new DodoException("Following task is already marked done:\n" + target.toString());
            }
        } else {
            if (!target.getMark()) {
                throw new DodoException("Following task is already marked undone:\n" + target.toString());
            }
        }
    }

    public static void main(String[] args) {
        int dodoheadCount = 0;
        Scanner userInput = new Scanner(System.in);
        String logo = " _____   ____  _____   ____  \n"
                + "|  __ \\ / __ \\|  __ \\ / __ \\ \n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |__| | |__| | |__| | |__| |\n"
                + "|_____/ \\____/|_____/ \\____/ \n";
        System.out.println(logo);
        System.out.println("Dododo do dododo do dododo.\n" + "What is your command?");
        while (true) {
            if (dodoheadCount > 3) {
                dodoheadCount = 0;
                System.out.println("You seem to be struggling. Type 'help' if you need to see the command list.");
            }
            String nextLine = userInput.nextLine();
            if (nextLine.equals("bye") || nextLine.equals("BB")) {
                System.out.println("K bye bye :)");
                break;
            } else if (nextLine.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("You have no tasks. Yay!");
                    continue;
                }
                System.out.println("Here are your tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    int taskNo = i + 1;
                    System.out.println(taskNo + ". " + tasks.get(i).toString());
                }
                continue;
            } else if (nextLine.isEmpty()) {
                dodoheadCount++;
                System.out.println("You've gotta enter a command first dodo head!");
                continue;
            } else if (nextLine.equals("help")) {
                report();
                continue;
            }

            String[] nextLineArr = nextLine.split("\\s", 2);
            if (nextLineArr.length == 1) {
                dodoheadCount++;
                System.out.println("Huh?");
                continue;
            }
            switch (nextLineArr[0]) {
                case "todo": {
                    Task newTask = new Todo(nextLineArr[1]);
                    tasks.add(newTask);
                    System.out.println("Added this to your list:\n" + newTask.toString() +
                            "\nYou now have " + tasks.size() + " task(s).");
                    break;
                }
                case "deadline": {
                    String[] details = nextLineArr[1].split(" /by ", 2);
                    try {
                        deadlineCommandCheck(details);
                    } catch (DodoException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }
                    Task newTask = new Deadline(details[0], details[1]);
                    tasks.add(newTask);
                    System.out.println("Added this to your list:\n" + newTask.toString() +
                            "\nYou now have " + tasks.size() + " task(s).");
                    break;
                }
                case "event": {
                    String[] details = nextLineArr[1].split(" \\/from | \\/to ", 3);
                    try {
                        eventCommandCheck(details);
                    } catch (DodoException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }
                    Task newTask = new Event(details[0], details[1], details[2]);
                    tasks.add(newTask);
                    System.out.println("Added this to your list:\n" + newTask.toString() +
                            "\nYou now have " + tasks.size() + " task(s).");
                    break;
                }
                case "mark": {
                    try {
                        markCommandCheck(nextLineArr);
                        int targetNo = Integer.parseInt(nextLineArr[1]);
                        validTaskNumber(targetNo);
                        redundantMark(targetNo, true);
                    } catch (DodoException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    } catch (NumberFormatException ex) {
                        System.out.println("Task number given was not a number");
                        break;
                    }
                    int targetNo = Integer.parseInt(nextLineArr[1]) - 1;
                    Task target = tasks.get(targetNo);
                    target.markDone();
                    System.out.println("Marked as done:\n" + target.toString());
                    break;
                }
                case "unmark": {
                    try {
                        markCommandCheck(nextLineArr);
                        int targetNo = Integer.parseInt(nextLineArr[1]);
                        validTaskNumber(targetNo);
                        redundantMark(targetNo, false);
                    } catch (DodoException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    } catch (NumberFormatException ex) {
                        System.out.println("Task number given was not a number");
                        break;
                    }
                    int targetNo = Integer.parseInt(nextLineArr[1]) - 1;
                    Task target = tasks.get(targetNo);
                    target.markUndone();
                    System.out.println("Marked as undone:\n" + target.toString());
                    break;
                }
                case "delete": {
                    try {
                        deleteCommandCheck(nextLineArr);
                        int targetNo = Integer.parseInt(nextLineArr[1]);
                        validTaskNumber(targetNo);
                    } catch (DodoException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    } catch (NumberFormatException ex) {
                        System.out.println("Task number given was not a number");
                        break;
                    }
                    int targetNo = Integer.parseInt(nextLineArr[1]) - 1;
                    Task target = tasks.get(targetNo);
                    tasks.remove(targetNo);
                    System.out.println("The following task has been destroyed:\n" + target.toString() +
                            "\nYou now have " + tasks.size() + " task(s).");
                    break;
                }
                default:
                    System.out.println("Huh?");
                    dodoheadCount++;
                    break;
            }
        }
    }

    private static void report() {
        System.out.println("You got it boss! Here you go:\n" +
                "list -> lists all current tasks and their numbering\n" +
                "todo 'name' -> adds a task called 'name'\n" +
                "deadline 'name' /by 'time' -> adds a task called 'name' with deadline of 'time'\n" +
                "event 'name' /from 'start' to 'end' -> adds a task called 'name' with timeframe from 'start' to 'end'\n" +
                "mark 'task number' -> marks corresponding task as done\n" +
                "unmark 'task number' -> marks corresponding task as undone\n" +
                "delete 'task number' -> removes corresponding task" +
                "bye -> dododo");
    }

}
