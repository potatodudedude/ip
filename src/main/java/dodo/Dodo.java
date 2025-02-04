package dodo;
import java.io.File;
import java.io.IOException;

public class Dodo {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    public Dodo(File storage) {
        this.storage = new Storage(storage);
        this.tasks = new TaskList();
        this.ui = new UI();
    }

    private void run() throws IOException {
        storage.existenceCheck();
        try {
            storage.readTo(tasks);
        } catch (DodoException ex) {
            ui.printError(ex.getMessage());
            System.out.println(ex.getMessage());
        }
        storage.update(tasks);
        ui.intro();
        boolean isExit = false;
        while (!isExit) {
            ui.dodoCheck();
            String nextLine = ui.readInput();
            Command nextCommand = Parse.parse(nextLine);
            nextCommand.execute(tasks, ui, storage);
            isExit = nextCommand.isExit();
        }
    }

    public static void main(String[] args) throws IOException {
        new Dodo(new File(System.getProperty("user.dir") + "/data/storage.txt")).run();
    }

}
