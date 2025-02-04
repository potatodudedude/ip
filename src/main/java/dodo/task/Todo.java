package dodo.task;

import dodo.task.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getStorageString() {
        if (this.isDone) {
            return "T|T|" + super.description;
        }
        return "T|F|" + super.description;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
