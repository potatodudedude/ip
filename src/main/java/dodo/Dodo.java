package dodo;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static dodo.TimeStringUtility.stringToLdt;
import static dodo.TimeStringUtility.stringToLd;

public class Dodo {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    public Dodo(File storage) {
        this.storage = new Storage(storage);
        this.tasks = new TaskList();
        this.ui = new UI();
    }

    private void markCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Mark/Unmark commands needs to be followed by single task number.\n" +
                    "e.g. mark 2");
        }
    }

    private void deleteCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Delete commands needs to be followed by single task number.\n" +
                    "e.g. delete 2");
        }
    }

    private void deadlineCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Deadline commands needs to be structured as follows:\n" +
                    "deadline 'name' /by 'time'\n" +
                    "'time' must be in the format yyyy-mm-dd hh:ss");
        }
    }

    private void eventCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 3) {
            throw new DodoException("Event commands needs to be structured as follows:\n" +
                    "event 'name' /from 'start time' /to 'end time'\n" +
                    "'time' must be in the format yyyy-mm-dd hh:ss");
        }
    }

    private void dueCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Due commands needs to be structured as follows:\n" +
                    "due yyyy-mm-dd");
        }
    }

    private void validTaskNumberCheck(int i) throws DodoException {
        if (i > tasks.size() || i < 1) {
            throw new DodoException("Task number " + i + " doesn't exist dodohead!");
        }
    }

    private void redundantMarkCheck(int targetNo, boolean isDone) throws DodoException {
        Task target = tasks.get(targetNo - 1);
        if (isDone) {
            if (target.getMark()) {
                throw new DodoException("Following task is already marked done:\n" + target.toString());
            }
        } else {
            if (!target.getMark()) {
                throw new DodoException("Following task is already marked undone:\n" + target.toString());
            }
        }
    }

    private void expiredTaskCheck(LocalDateTime time) throws DodoException {
        if (time.isBefore(LocalDateTime.now())) {
            throw new DodoException("This task is already expired... Oops :P");
        }
    }
    private void validEventTimeCheck(LocalDateTime start, LocalDateTime end) throws DodoException {
        if (start.isAfter(end)) {
            throw new DodoException("This event ends before it begins! How can this be? :O");
        }
    }

    private void run() throws IOException {
        storage.existenceCheck();
        try {
            storage.readTo(tasks);
        } catch (DodoException ex) {
            System.out.println(ex.getMessage());
        }
        storage.update(tasks);
        ui.intro();
        while (true) {
            ui.dodoCheck();
            String nextLine = ui.readInput();
            if (nextLine.equals("bye") || nextLine.equals("bb")) {
                ui.bye();
                break;
            } else if (nextLine.equals("list")) {
                if (tasks.isEmpty()) {
                    ui.noTask();
                    continue;
                }
                ui.taskHeader();
                ui.printTaskList(tasks);
                continue;
            } else if (nextLine.isEmpty()) {
                ui.dodoHead();
                ui.emptyCommand();
                continue;
            } else if (nextLine.equals("help")) {
                ui.report();
                continue;
            }

            String[] nextLineArr = nextLine.split("\\s", 2);
            if (nextLineArr.length == 1) {
                ui.dodoHead();
                ui.invalidCommand();
                continue;
            }
            switch (nextLineArr[0]) {
            case "todo": {
                Task newTask = new Todo(nextLineArr[1]);
                tasks.addTask(newTask);
                storage.update(tasks);
                ui.updateTaskList(tasks, newTask);
                break;
            }
            case "deadline": {
                String[] details = nextLineArr[1].split(" /by ", 2);
                LocalDateTime time;
                try {
                    deadlineCommandCheck(details);
                    time = stringToLdt(details[1]);
                    expiredTaskCheck(time);
                } catch (DodoException ex) {
                    System.out.println(ex.getMessage());
                    break;
                }
                Task newTask = new Deadline(details[0], time);
                tasks.addTask(newTask);
                storage.update(tasks);
                ui.updateTaskList(tasks, newTask);
                break;
            }
            case "event": {
                String[] details = nextLineArr[1].split(" \\/from | \\/to ", 3);
                LocalDateTime start;
                LocalDateTime end;
                try {
                    eventCommandCheck(details);
                    start = stringToLdt(details[1]);
                    end = stringToLdt(details[2]);
                    validEventTimeCheck(start, end);
                    expiredTaskCheck(end);
                } catch (DodoException ex) {
                    System.out.println(ex.getMessage());
                    break;
                }
                Task newTask = new Event(details[0], start, end);
                tasks.addTask(newTask);
                storage.update(tasks);
                ui.updateTaskList(tasks, newTask);
                break;
            }
            case "mark": {
                try {
                    markCommandCheck(nextLineArr);
                    int targetNo = Integer.parseInt(nextLineArr[1]);
                    validTaskNumberCheck(targetNo);
                    redundantMarkCheck(targetNo, true);
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
                storage.update(tasks);
                ui.updateMark(target, true);
                break;
            }
            case "unmark": {
                try {
                    markCommandCheck(nextLineArr);
                    int targetNo = Integer.parseInt(nextLineArr[1]);
                    validTaskNumberCheck(targetNo);
                    redundantMarkCheck(targetNo, false);
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
                storage.update(tasks);
                ui.updateMark(target, false);
                break;
            }
            case "delete": {
                try {
                    deleteCommandCheck(nextLineArr);
                    int targetNo = Integer.parseInt(nextLineArr[1]);
                    validTaskNumberCheck(targetNo);
                } catch (DodoException ex) {
                    System.out.println(ex.getMessage());
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Task number given was not a number");
                    break;
                }
                int targetNo = Integer.parseInt(nextLineArr[1]) - 1;
                Task target = tasks.get(targetNo);
                tasks.removeTask(targetNo);
                storage.update(tasks);
                ui.updateDelete(tasks, target);
                break;
            }
            case "due": {
                LocalDate date;
                try {
                    dueCommandCheck(nextLineArr);
                    date = stringToLd(nextLineArr[1]);
                } catch (DodoException ex) {
                    System.out.println(ex.getMessage());
                    break;
                }
                TaskList filteredList = tasks.findByDate(date);
                ui.updateDue(date);
                ui.printTaskList(filteredList);
            }
            break;
            default:
                ui.invalidCommand();
                ui.dodoHead();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Dodo(new File(System.getProperty("user.dir") + "/data/storage.txt")).run();
    }

}
