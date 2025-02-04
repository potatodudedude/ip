package dodo;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.bye();
    }
}
