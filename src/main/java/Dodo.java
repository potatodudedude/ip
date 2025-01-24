import java.util.Scanner;
import java.util.ArrayList;

public class Dodo {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        String regex = "\\s";
        String logo = " _____   ____  _____   ____  \n"
                + "|  __ \\ / __ \\|  __ \\ / __ \\ \n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |__| | |__| | |__| | |__| |\n"
                + "|_____/ \\____/|_____/ \\____/ \n";
        System.out.println(logo);
        System.out.println("Hello! This is Dodo.\n" + "What is your command?");
        while (true) {
            String nextLine = userInput.nextLine();
            if (nextLine.equals("bye") || nextLine.equals("Bye")) {
                System.out.println("See You! :)");
                break;
            }
            if (nextLine.isEmpty()) {
                System.out.println("You've gotta enter a command first dodo head!");
                continue;
            }
            String[] nextLineArr = nextLine.split(regex, 2);
//            for (int i = 0; i < nextLineArr.length; i++) {
//                System.out.println(nextLineArr[i]);
//            }
            switch (nextLineArr[0]) {
                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("You have no tasks. Yay!");
                        break;
                    }
                    System.out.println("Here are your tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        int taskNo = i + 1;
                        System.out.println(taskNo + ". " + tasks.get(i).toString());
                    }
                    break;
                case "todo":
                    Task newTask = new Task(nextLineArr[1]);
                    tasks.add(newTask);
                    System.out.println("Added this to your list:\n" + newTask.toString());
                    break;
                case "mark":
                    try{
                        int targetNo = Integer.parseInt(nextLineArr[1]);
                    }
                    catch (NumberFormatException ex){
                        System.out.println("Mark command needs to be done as follows without brackets:\n" +
                                "mark (task number)");
                        break;
                    }
                    int doneTarget = Integer.parseInt(nextLineArr[1]);
                    if (doneTarget > tasks.size() || doneTarget < 1) {
                        System.out.println("Task number " + doneTarget + " doesn't exist dodohead!");
                        break;
                    }
                    doneTarget--;
                    Task doneTask = tasks.get(doneTarget);
                    if (doneTask.getMark()) {
                        System.out.println("Following task is already marked done:\n" + doneTask.toString());
                        break;
                    }
                    doneTask.markDone();
                    System.out.println("Marked as done:\n" + doneTask.toString());
                    break;
                case "unmark":
                    try{
                        int targetNo = Integer.parseInt(nextLineArr[1]);
                    }
                    catch (NumberFormatException ex){
                        System.out.println("Unmark command needs to be done as follows without brackets:\n" +
                                "unmark (task number)");
                        break;
                    }
                    int undoneTarget = Integer.parseInt(nextLineArr[1]);
                    if (undoneTarget > tasks.size() || undoneTarget < 1) {
                        System.out.println("Task number " + undoneTarget + " doesn't exist dodohead!");
                        break;
                    }
                    undoneTarget--;
                    Task undoneTask = tasks.get(undoneTarget);
                    if (!undoneTask.getMark()) {
                        System.out.println("Following task is already marked undone:\n" + undoneTask.toString());
                        break;
                    }
                    undoneTask.markUndone();
                    System.out.println("Marked as undone:\n" + undoneTask.toString());
                    break;
                default:
                    System.out.println("Sorry, I don't recognise this command :(");
                    break;
            }
        }
    }
}
