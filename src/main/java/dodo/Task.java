package dodo;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getMark() {
        return isDone;
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
