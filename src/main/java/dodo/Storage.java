package dodo;

import static dodo.utilities.TimeStringUtility.stringToLdt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import dodo.task.Deadline;
import dodo.task.Event;
import dodo.task.TaskList;
import dodo.task.Todo;
import dodo.utilities.DodoException;

/**
 * Class for writing files to hard disc storage and for reading from the disc.
 */
public class Storage {
    private File storage;

    public Storage(File storage) {
        this.storage = storage;
    }

    public File getFile() {
        return storage;
    }

    /**
     * Checks if storage file and directory exists, if not, it is created.
     */
    public void existenceCheck() {
        try {
            if (!storage.exists()) {
                if (storage.getParentFile() != null) {
                    storage.getParentFile().mkdirs();
                }
                storage.createNewFile();;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Converts a string of "T" or "F" to its corresponding boolean.
     *
     * @throws DodoException Throws when input is not "T" or "F"
     */
    private boolean stringToBoolean(String line) throws DodoException {
        switch (line) {
        case "T":
            return true;
        case "F":
            return false;
        default:
            throw new DodoException("Incorrect done marking formatting");
        }
    }

    /**
     * Reads the storage file and adds all the saved tasks to the parameter TaskList
     * Converts the storage file lines into instructions, then creates and adds the Tasks
     *
     * @param tasks TaskList to be added to
     * @throws FileNotFoundException
     * @throws DodoException Throws when storage file format is not valid
     */
    public void readTo(TaskList tasks) throws FileNotFoundException, DodoException {
        Scanner storageScanner = new Scanner(storage);
        while (storageScanner.hasNextLine()) {
            String line = storageScanner.nextLine();
            String[] lineArr = line.split("\\|");
            int len = lineArr.length;

            if (len < 3 || len > 5) {
                storageScanner.close();
                throw new DodoException("Incorrect storage formatting");
            }

            switch (lineArr[0]) {
            case "T":
                if (len != 3) {
                    storageScanner.close();
                    throw new DodoException("Incorrect storage formatting");
                }
                tasks.addTask(new Todo(lineArr[2], stringToBoolean(lineArr[1])));
                break;
            case "D":
                if (len != 4) {
                    storageScanner.close();
                    throw new DodoException("Incorrect storage formatting");
                }
                tasks.addTask(new Deadline(lineArr[2], stringToLdt(lineArr[3]), stringToBoolean(lineArr[1])));
                break;
            case "E":
                if (len != 5) {
                    storageScanner.close();
                    throw new DodoException("Incorrect storage formatting");
                }
                tasks.addTask(new Event(lineArr[2], stringToLdt(lineArr[3]), stringToLdt(lineArr[4]),
                        stringToBoolean(lineArr[1])));
                break;
            default:
                storageScanner.close();
                throw new DodoException("Incorrect storage formatting");
            }
        }
        storageScanner.close();
    }

    /**
     * Reads a given TaskList and writes to storage
     * A new storage is file is created and the old one is deleted
     *
     * @param tasks
     */
    public void updateTaskListFromStorage(TaskList tasks) {
        File temp = new File(System.getProperty("user.dir") + "/data/temp.txt");
        BufferedWriter sW;
        try {
            sW = new BufferedWriter(new FileWriter(temp));
            for (int i = 0; i < tasks.size(); i++) {
                sW.write(tasks.get(i).getStorageString() + System.lineSeparator());
            }
            sW.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        if (!storage.delete()) {
            System.out.println("Cannot delete file");
        }
        if (!temp.renameTo(storage)) {
            System.out.println("Cannot rename file");
        }
    }
}
