package dodo;

import java.io.*;
import java.util.Scanner;

import static dodo.TimeStringUtility.stringToLdt;

public class Storage {
    private File storage;

    public Storage(File storage) {
        this.storage = storage;
    }

    public File getFile() {
        return storage;
    }

    public void existenceCheck() {
        try {
            if (!storage.exists()) {
                if (storage.getParentFile() != null) {
                    storage.getParentFile().mkdirs();
                }
                storage.createNewFile();;
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

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

    public void update(TaskList tasks) {
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
