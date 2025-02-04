package dodo.utilities;

import java.time.LocalDateTime;

import dodo.task.Task;
import dodo.task.TaskList;



public class DodoCheck {
    public static void markCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Mark/Unmark commands needs to be followed by single task number.\n"
                    + "e.g. mark 2");
        }
    }

    public static void deleteCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Delete commands needs to be followed by single task number.\n"
                    + "e.g. delete 2");
        }
    }
    public static void deadlineCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Deadline commands needs to be structured as follows:\n"
                    + "deadline 'name' /by 'time'\n"
                    + "'time' must be in the format yyyy-mm-dd hh:ss");
        }
    }

    public static void eventCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 3) {
            throw new DodoException("Event commands needs to be structured as follows:\n"
                    + "event 'name' /from 'start time' /to 'end time'\n"
                    + "'time' must be in the format yyyy-mm-dd hh:ss");
        }
    }

    public static void dueCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Due commands needs to be structured as follows:\n"
                    + "due yyyy-mm-dd");
        }
    }

    public static int taskNumberParse(String line) throws DodoException {
        int result;
        try {
            result = Integer.parseInt(line);
        } catch (NumberFormatException ex) {
            throw new DodoException("Task number given was not a number");
        }
        return result;
    }

    public static void validTaskNumberCheck(int i, TaskList tasks) throws DodoException {
        if (i > tasks.size() || i < 1) {
            throw new DodoException("Task number " + i + " doesn't exist dodohead!");
        }
    }

    public static void redundantMarkCheck(int targetNo, boolean isDone, TaskList tasks) throws DodoException {
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

    public static void expiredTaskCheck(LocalDateTime time) throws DodoException {
        if (time.isBefore(LocalDateTime.now())) {
            throw new DodoException("This task is already expired... Oops :P");
        }
    }
    public static void validEventTimeCheck(LocalDateTime start, LocalDateTime end) throws DodoException {
        if (start.isAfter(end)) {
            throw new DodoException("This event ends before it begins! How can this be? :O");
        }
    }
}
