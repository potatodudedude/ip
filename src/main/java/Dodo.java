import java.util.Scanner;
import java.util.ArrayList;

public class Dodo {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
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
            switch (nextLine) {
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
                default:
                    Task newTask = new Task(nextLine);
                    tasks.add(newTask);
                    System.out.println("Task (" + nextLine + ") added to your list");
                    break;
            }
        }
    }
}
