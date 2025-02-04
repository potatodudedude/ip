package dodo.task;

/**
 * Task class to represent tasks.
 * Contains name and completion status.
 * Has its own toString() format.
 * Has method for formatting lines to write to storage.
 */
public class Task {
    protected String description;
    protected boolean isDone;

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

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string that stores the data of this task for writing into storage.
     */
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
