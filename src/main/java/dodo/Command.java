package dodo;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }

}
