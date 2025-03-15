package concurrency.code.meesho.sprintplamer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SprintManager {
    private Map<String, Sprint> sprints = new HashMap<>();

    public Sprint createSprint(String sprintId) {
        Sprint sprint = new Sprint(sprintId);
        sprints.put(sprintId, sprint);
        return sprint;
    }

    public boolean addTaskToSprint(String sprintId, Task task) throws Exception {
        Sprint sprint = sprints.get(sprintId);
        if (sprint != null && (task.getStatus() == TaskStatus.IN_PROGRESS && countInProgressTasks(sprint) < 2)) {
            return sprint.addTask(task);
        }
        throw new Exception("Cannot add more than 2 tasks in IN_PROGRESS status.");
    }

    private int countInProgressTasks(Sprint sprint) {
        return (int) sprint.getTasks().values().stream()
                .filter(task -> task.getStatus() == TaskStatus.IN_PROGRESS)
                .count();
    }

    public List<Task> getTasksForUserInSprint(String sprintId, String userId) {
        Sprint sprint = sprints.get(sprintId);
        return sprint != null ? sprint.getTasksForUser(userId) : Collections.emptyList();
    }

    public List<Task> getDelayedTasksInSprint(String sprintId) {
        Sprint sprint = sprints.get(sprintId);
        return sprint != null ? sprint.getDelayedTasks() : Collections.emptyList();
    }
}
