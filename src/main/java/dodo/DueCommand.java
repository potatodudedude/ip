package dodo;

import java.time.LocalDate;

import static dodo.TimeStringUtility.stringToLd;

public class DueCommand extends Command {
    private String[] contents;

    public DueCommand(String[] contents) {
        super(false);
        this.contents = contents;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        LocalDate date;
        try {
            DodoCheck.dueCommandCheck(contents);
            date = stringToLd(contents[1]);
        } catch (DodoException ex) {
            ui.printError(ex.getMessage());
            return;
        }
        TaskList filteredList = tasks.findByDate(date);
        ui.updateDue(date);
        ui.printTaskList(filteredList);
    }
}
