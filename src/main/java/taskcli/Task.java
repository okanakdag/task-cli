package taskcli;
import java.time.LocalDateTime;

import taskcli.enums.Status;

public class Task {

    private final int id;

    private String description;

    private Status status;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // New task constructor
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Loading constructor
    public Task(int id, String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = Status.valueOf(status);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isDone() {
        return status == Status.DONE;
    }

    @Override
    public String toString(){
        return "Task id: " + id +
                "\nDescription: " + description +
                "\nStatus: " + status +
                "\nCreated at " + createdAt +
                "\nLast updated at " + updatedAt;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}