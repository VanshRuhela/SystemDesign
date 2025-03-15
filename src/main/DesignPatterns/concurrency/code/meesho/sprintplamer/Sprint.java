package concurrency.code.meesho.sprintplamer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sprint {
    private String sprintId;
    private Map<String, Task> tasks = new HashMap<>();
    private int maxInProgressTasks = 2;
    private int maxTasks = 20;

    public Sprint(String sprintId){
        this.sprintId = sprintId;
    }
    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public void setTasks(Map<String, Task> tasks) {
        this.tasks = tasks;
    }

    public int getMaxInProgressTasks() {
        return maxInProgressTasks;
    }

    public void setMaxInProgressTasks(int maxInProgressTasks) {
        this.maxInProgressTasks = maxInProgressTasks;
    }

    public int getMaxTasks() {
        return maxTasks;
    }

    public void setMaxTasks(int maxTasks) {
        this.maxTasks = maxTasks;
    }

    public boolean addTask(Task task) {
        if (tasks.size() < maxTasks) {
            tasks.put(task.getTaskId(), task);
            return true;
        }
        return false;
    }

    public boolean removeTask(String taskId) {
        return tasks.remove(taskId) != null;
    }

    public List<Task> getTasksForUser(String userId) {
        return tasks.values().stream()
                .filter(task -> task.getAssignedUser().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Task> getDelayedTasks() {
        // Assume a method to get tasks that are delayed based on criteria.
//        return tasks.values().stream()
//                .filter(task -> /* delayed criteria */)
//                .collect(Collectors.toList());
        return null;
    }
}
