package dodo;

public class InvalidCommand extends Command {
    private int type;

    public InvalidCommand(int type) {
        super(false);
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        switch(type) {
        case 0: // No command line
            ui.dodoHead();
            ui.emptyCommand();
            break;
        case 1: // Unrecognised command
            ui.dodoHead();
            ui.invalidCommand();
            break;
        }

    }
}
