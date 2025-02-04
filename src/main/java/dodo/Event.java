package dodo;

public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getStorageString() {
        if (this.isDone) {
            return "E|T|" + super.description + "|" + start + "|" + end;
        }
        return "E|F|" + super.description + "|" + start + "|" + end;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
