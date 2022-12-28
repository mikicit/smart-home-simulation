package dev.mikita.sh.core.task;

import java.util.LinkedList;
import java.util.Queue;

public class TaskSystem {
    private final Queue<Task> tasks = new LinkedList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask() {
        return tasks.poll();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
