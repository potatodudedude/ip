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
     * Returns the tasks in the TaskList.
     */
    public String taskPrinter() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int taskNo = i + 1;
            result.append(taskNo).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Looks through the TaskList and returns a new TaskList filtered by matching expiry dates.
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
                LocalDate start = ((Event) task).getStart().toLocalDate();
                LocalDate end = ((Event) task).getEnd().toLocalDate();
                boolean isBetween = date.isBefore(end) && date.isAfter(start);
                boolean isOn = date.isEqual(start) || date.isEqual(end);
                if (isBetween || isOn) {
                    filteredList.addTask(task);
                }
            }
        }
        return filteredList;
    }

    /**
     * Looks through the TaskList and returns a new TaskList filtered by descriptions superstring of the key string.
     */
    public TaskList findByDescription(String line) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(line)) {
                filteredList.addTask(task);
            }
        }
        return filteredList;
    }
}
