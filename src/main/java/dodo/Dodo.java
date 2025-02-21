package dodo;

import java.io.File;
import java.io.IOException;

import dodo.command.Command;
import dodo.task.TaskList;
import dodo.utilities.DodoException;
import dodo.utilities.TextColourPair;

/**
 * Class where the dodo chatbot initialises from.
 * Provides the base for the other class objects.
 */
public class Dodo {
    private TaskList tasks;
    private UI ui;
    private Storage storage;
    private String textColour;

    /**
     * Constructor that initialises with specified storage poth.
     */
    public Dodo(File storage) {
        this.storage = new Storage(storage);
        this.tasks = new TaskList();
        this.ui = new UI();
    }

    /**
     * Overloaded constructor that initialises without parameters.
     */
    public Dodo() {
        this.storage = new Storage(new File("./data/storage.txt"));
        this.tasks = new TaskList();
        this.ui = new UI();
    }

    /**
     * Executes the main running loop of Dodo
     *
     * @throws IOException
     */
    public void run() throws IOException {
        storage.existenceCheck();
        try {
            storage.readTo(tasks);
        } catch (DodoException ex) {
            System.out.println(ex.getMessage());
        }
        storage.updateTaskListFromStorage(tasks);
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

    /**
     * Generates a response for the user's chat message. Temporary for javafx testing
     */
    public String getResponse(String input) {
        Command nextCommand = Parse.parse(input);
        TextColourPair response = nextCommand.execute(tasks, ui, storage);
        this.textColour = response.getColour();
        return response.getText();
    }

    public TaskList getTasks() {
        return this.tasks;
    }
    public UI getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public String getTextColour() {
        return this.textColour;
    }
}
