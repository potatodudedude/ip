package dodo;

public class Deadline extends Task{
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String getStorageString() {
        if (this.isDone) {
            return "D|T|" + super.description + "|" + time;
        }
        return "D|F|" + super.description + "|" + time;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
