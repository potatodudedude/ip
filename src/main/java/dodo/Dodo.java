package dodo;
import dodo.command.Command;
import dodo.task.TaskList;
import dodo.utilities.DodoException;

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

    /**
     * Executes the main running loop of Dodo
     *
     * @throws IOException
     */
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

    /**
     * Initialises Dodo by specifying storage file path
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Dodo(new File("./data/storage.txt")).run();
    }

}
