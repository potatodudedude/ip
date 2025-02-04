package dodo;

import java.util.ArrayList;

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

    public void taskPrinter() {
        for (int i = 0; i < tasks.size(); i++) {
            int taskNo = i + 1;
            System.out.println(taskNo + ". " + tasks.get(i).toString());
        }
    }
}
