package dodo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected final DateTimeFormatter PRESENT_DTF = DateTimeFormatter.ofPattern("HH:mm dd-MMM-yyyy");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean getMark() {
        return isDone;
    }

    protected String ldtToString(LocalDateTime ldt) {
        return ldt.getHour() + ldt.getMinute() + ldt.getDayOfMonth() + ldt.getMonth().toString() + ldt.getYear();
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getStorageString() {
        return "";
    }

    @Override
    public String toString() {
        String doneMarker;
        if (isDone) {
            doneMarker = "[X]";
        } else {
            doneMarker = "[ ]";
        }
        return doneMarker + " " + description;
    }
}
