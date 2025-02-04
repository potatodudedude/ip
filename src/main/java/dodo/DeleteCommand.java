package dodo;

public class DeleteCommand extends Command {
    private String[] contents;
    public DeleteCommand(String[] contents) {
        super(false);
        this.contents = contents;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int targetNo;
        try {
            DodoCheck.deleteCommandCheck(contents);
            targetNo = DodoCheck.taskNumberParse(contents[1]);
            DodoCheck.validTaskNumberCheck(targetNo, tasks);
        } catch (DodoException ex) {
            ui.printError(ex.getMessage());
            return;
        }
        targetNo = Integer.parseInt(contents[1]) - 1;
        Task target = tasks.get(targetNo);
        tasks.removeTask(targetNo);
        storage.update(tasks);
        ui.updateDelete(tasks, target);
    }
}
