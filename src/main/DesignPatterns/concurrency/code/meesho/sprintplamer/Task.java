package concurrency.code.meesho.sprintplamer;

public class Task {
    private String taskId;
    private TaskType type;
    private TaskStatus status;
    private String assignedUser;

    // Constructor, Getters, and Setters

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public void updateStatus(TaskStatus newStatus) throws Exception {
        // Ensure valid status transitions
        if ((status == TaskStatus.TODO && newStatus == TaskStatus.IN_PROGRESS) ||
                (status == TaskStatus.IN_PROGRESS && (newStatus == TaskStatus.TODO || newStatus == TaskStatus.DONE))) {
            this.status = newStatus;
        } else {
            throw new Exception("Invalid status transition");
        }
    }
}
