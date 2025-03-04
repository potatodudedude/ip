package dodo.task;

import static dodo.utilities.TimeStringUtility.DTF;
import static dodo.utilities.TimeStringUtility.PRESENTATION_DTF;

import java.time.LocalDateTime;

/**
 * Task subclass for tasks with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor that marks task as not done by default.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor that marks task's isDone as indicated.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String getStorageString() {
        if (this.isDone) {
            return "E|T|" + super.description + "|" + start.format(DTF) + "|" + end.format(DTF);
        }
        return "E|F|" + super.description + "|" + start.format(DTF) + "|" + end.format(DTF);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(PRESENTATION_DTF) + " to: "
                + end.format(PRESENTATION_DTF) + ")";
    }
}
