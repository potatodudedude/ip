package dodo.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class that contains an ArrayList for Task objects
 * All methods for manipulating tasks are here.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Prints out the tasks in the TaskList.
     */
    public void taskPrinter() {
        for (int i = 0; i < tasks.size(); i++) {
            int taskNo = i + 1;
            System.out.println(taskNo + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Looks through the TaskList and rrturns a new TaskList filtered by matching expiry dates.
     */
    public TaskList findByDate(LocalDate date) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                if (((Deadline) task).getTime().toLocalDate().isEqual(date)) {
                    filteredList.addTask(task);
                }
            } else if (task instanceof Event) {
                if (((Event) task).getEnd().toLocalDate().isEqual(date)) {
                    filteredList.addTask(task);
                }
            }
        }
        return filteredList;
    }
}
