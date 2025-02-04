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

    private void run() throws IOException {
        storage.existenceCheck();
        try {
            storage.readTo(tasks);
        } catch (DodoException ex) {
            ui.printError(ex.getMessage());
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
                    Parse.deadlineCommandCheck(details);
                    time = stringToLdt(details[1]);
                    Parse.expiredTaskCheck(time);
                } catch (DodoException ex) {
                    ui.printError(ex.getMessage());
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
                    Parse.eventCommandCheck(details);
                    start = stringToLdt(details[1]);
                    end = stringToLdt(details[2]);
                    Parse.validEventTimeCheck(start, end);
                    Parse.expiredTaskCheck(end);
                } catch (DodoException ex) {
                    ui.printError(ex.getMessage());
                    break;
                }
                Task newTask = new Event(details[0], start, end);
                tasks.addTask(newTask);
                storage.update(tasks);
                ui.updateTaskList(tasks, newTask);
                break;
            }
            case "mark": {
                int targetNo;
                try {
                    Parse.markCommandCheck(nextLineArr);
                    targetNo = Parse.taskNumberParse(nextLineArr[1]);
                    Parse.validTaskNumberCheck(targetNo, tasks);
                    Parse.redundantMarkCheck(targetNo, true, tasks);
                } catch (DodoException ex) {
                    ui.printError(ex.getMessage());
                    break;
                }
                targetNo = Integer.parseInt(nextLineArr[1]) - 1;
                Task target = tasks.get(targetNo);
                target.markDone();
                storage.update(tasks);
                ui.updateMark(target, true);
                break;
            }
            case "unmark": {
                int targetNo;
                try {
                    Parse.markCommandCheck(nextLineArr);
                    targetNo = Parse.taskNumberParse(nextLineArr[1]);
                    Parse.validTaskNumberCheck(targetNo, tasks);
                    Parse.redundantMarkCheck(targetNo, false, tasks);
                } catch (DodoException ex) {
                    ui.printError(ex.getMessage());
                    break;
                }
                targetNo = Integer.parseInt(nextLineArr[1]) - 1;
                Task target = tasks.get(targetNo);
                target.markUndone();
                storage.update(tasks);
                ui.updateMark(target, false);
                break;
            }
            case "delete": {
                int targetNo;
                try {
                    Parse.deleteCommandCheck(nextLineArr);
                    targetNo = Parse.taskNumberParse(nextLineArr[1]);
                    Parse.validTaskNumberCheck(targetNo, tasks);
                } catch (DodoException ex) {
                    ui.printError(ex.getMessage());
                    break;
                }
                targetNo = Integer.parseInt(nextLineArr[1]) - 1;
                Task target = tasks.get(targetNo);
                tasks.removeTask(targetNo);
                storage.update(tasks);
                ui.updateDelete(tasks, target);
                break;
            }
            case "due": {
                LocalDate date;
                try {
                    Parse.dueCommandCheck(nextLineArr);
                    date = stringToLd(nextLineArr[1]);
                } catch (DodoException ex) {
                    ui.printError(ex.getMessage());
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
