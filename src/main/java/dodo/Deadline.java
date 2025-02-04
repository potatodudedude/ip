package dodo;

public class Deadline extends Task{
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStorageString() {
        return "D|" + super.description + "|" + time;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
