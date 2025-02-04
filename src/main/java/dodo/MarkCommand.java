package dodo;

public class MarkCommand extends Command {
    private int type;
    private String[] contents;
    public MarkCommand(int type, String[] contents) {
        super(false);
        this.type = type;
        this.contents = contents;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (type == 0) { // mark
            int targetNo;
            try {
                DodoCheck.markCommandCheck(contents);
                targetNo = DodoCheck.taskNumberParse(contents[1]);
                DodoCheck.validTaskNumberCheck(targetNo, tasks);
                DodoCheck.redundantMarkCheck(targetNo, true, tasks);
            } catch (DodoException ex) {
                ui.printError(ex.getMessage());
                return;
            }
            targetNo = Integer.parseInt(contents[1]) - 1;
            Task target = tasks.get(targetNo);
            target.markDone();
            storage.update(tasks);
            ui.updateMark(target, true);
        } else if (type == 1) { // unmark
            int targetNo;
            try {
                DodoCheck.markCommandCheck(contents);
                targetNo = DodoCheck.taskNumberParse(contents[1]);
                DodoCheck.validTaskNumberCheck(targetNo, tasks);
                DodoCheck.redundantMarkCheck(targetNo, false, tasks);
            } catch (DodoException ex) {
                ui.printError(ex.getMessage());
                return;
            }
            targetNo = Integer.parseInt(contents[1]) - 1;
            Task target = tasks.get(targetNo);
            target.markUndone();
            storage.update(tasks);
            ui.updateMark(target, false);
        }
    }
}
