package dodo;


import dodo.command.*;

public class Parse {

    public static Command parse(String line) {
        if (line.equals("bye") || line.equals("bb")) {
            return new ByeCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (line.isEmpty()) {
            return new InvalidCommand(0);
        } else if (line.equals("help")) {
            return new HelpCommand();
        }

        String[] nextLineArr = line.split("\\s", 2);
        if (nextLineArr.length == 1) {
            return new InvalidCommand(1);
        }
        switch (nextLineArr[0]) {
        case "todo":
            return new AddCommand(0, nextLineArr[1]);
        case "deadline":
            return new AddCommand(1, nextLineArr[1]);
        case "event":
            return new AddCommand(2,  nextLineArr[1]);
        case "mark":
            return new MarkCommand(0, nextLineArr);
        case "unmark":
            return new MarkCommand(1, nextLineArr);
        case "delete":
            return new DeleteCommand(nextLineArr);
        case "due":
            return new DueCommand(nextLineArr);
        default:
            return new InvalidCommand(1);
        }
    }
}
