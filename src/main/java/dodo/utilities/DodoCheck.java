package dodo.utilities;

import dodo.task.Task;
import dodo.task.TaskList;
import dodo.utilities.DodoException;

import java.time.LocalDateTime;

/**
 * Group of methods to check for valid formatting/redudancy in user commands.
 */
public class DodoCheck {

    /**
     * Checks if parsed command line is appropriate length for its type(Marking).
     *
     * @throws DodoException If not appropriate.
     */
    public static void markCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Mark/Unmark commands needs to be followed by single task number.\n" +
                    "e.g. mark 2");
        }
    }

    /**
     * Checks if parsed command line is appropriate length for its type(delete).
     *
     * @throws DodoException If not appropriate.
     */
    public static void deleteCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Delete commands needs to be followed by single task number.\n" +
                    "e.g. delete 2");
        }
    }

    /**
     * Checks if parsed command line is appropriate length for its type(deadline).
     *
     * @throws DodoException If not appropriate.
     */
    public static void deadlineCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Deadline commands needs to be structured as follows:\n" +
                    "deadline 'name' /by 'time'\n" +
                    "'time' must be in the format yyyy-mm-dd hh:ss");
        }
    }

    /**
     * Checks if parsed command line is appropriate length for its type(event).
     *
     * @throws DodoException If not appropriate.
     */
    public static void eventCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 3) {
            throw new DodoException("Event commands needs to be structured as follows:\n" +
                    "event 'name' /from 'start time' /to 'end time'\n" +
                    "'time' must be in the format yyyy-mm-dd hh:ss");
        }
    }

    /**
     * Checks if parsed command line is appropriate length for its type(due).
     *
     * @throws DodoException If not appropriate.
     */
    public static void dueCommandCheck(String[] commands) throws DodoException {
        if (commands.length != 2) {
            throw new DodoException("Due commands needs to be structured as follows:\n" +
                    "due yyyy-mm-dd");
        }
    }

    /**
     * Checks if task number string given is actually a number.
     *
     * @throws DodoException If not a number.
     */
    public static int taskNumberParse(String line) throws DodoException{
        int result;
        try {
            result = Integer.parseInt(line);
        } catch (NumberFormatException ex) {
            throw new DodoException("Task number given was not a number");
        }
        return result;
    }

    /**
     * Checks if task number i exists currently.
     *
     * @throws DodoException If no such task exists.
     */
    public static void validTaskNumberCheck(int i, TaskList tasks) throws DodoException {
        if (i > tasks.size() || i < 1) {
            throw new DodoException("Task number " + i + " doesn't exist dodohead!");
        }
    }

    /**
     * Given the parameters, checks if we would be marking/unmarking a task that is already marked/unmarked.
     *
     * @param targetNo Task number.
     * @param isDone If command given is to mark or unmark.
     * @param tasks TaskList of tasks.
     * @throws DodoException If marking/unmarking is redundant.
     */
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

    /**
     * Checks if time given has already past.
     *
     * @throws DodoException If time is past.
     */
    public static void expiredTaskCheck(LocalDateTime time) throws DodoException {
        if (time.isBefore(LocalDateTime.now())) {
            throw new DodoException("This task is already expired... Oops :P");
        }
    }

    /**
     * Checks if event's start timing is before its end timing.
     *
     * @throws DodoException If not appropriate.
     */
    public static void validEventTimeCheck(LocalDateTime start, LocalDateTime end) throws DodoException {
        if (start.isAfter(end)) {
            throw new DodoException("This event ends before it begins! How can this be? :O");
        }
    }
}
