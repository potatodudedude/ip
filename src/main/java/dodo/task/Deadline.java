package dodo.task;

import static dodo.utilities.TimeStringUtility.DTF;
import static dodo.utilities.TimeStringUtility.PRESENTATION_DTF;

import java.time.LocalDateTime;

/**
 * Task subclass for tasks with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime time;

    /**
     * Constructor that marks task as not done by default.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructor that marks task's isDone as indicated.
     */
    public Deadline(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String getStorageString() {
        if (this.isDone) {
            return "D|T|" + super.description + "|" + time.format(DTF);
        }
        return "D|F|" + super.description + "|" + time.format(DTF);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(PRESENTATION_DTF) + ")";
    }
}
