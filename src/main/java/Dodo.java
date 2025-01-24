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
                case "add":
                    Task newTask = new Task(nextLineArr[1]);
                    tasks.add(newTask);
                    System.out.println("Added this to your list:\n" + newTask.toString());
                    break;

                default:
                    System.out.println("Sorry, I don't recognise this command :(");
                    break;
            }
        }
    }
}
