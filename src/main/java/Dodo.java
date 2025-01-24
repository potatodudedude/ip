import java.util.Scanner;

public class Dodo {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String logo = " _____   ____  _____   ____  \n"
                + "|  __ \\ / __ \\|  __ \\ / __ \\ \n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |  | | |  | | |  | | |  | |\n"
                + "| |__| | |__| | |__| | |__| |\n"
                + "|_____/ \\____/|_____/ \\____/ \n";
        System.out.println(logo);
        System.out.println("Hello! This is Dodo.\n" + "What is your command?\n");
        while (true) {
            String nextLine = userInput.nextLine();
            if (nextLine.equals("bye")) {
                System.out.println("See You! :)\n");
                break;}
            System.out.println(nextLine);
        }
    }
}
