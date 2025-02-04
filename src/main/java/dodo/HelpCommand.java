package dodo;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.report();
    }
}
