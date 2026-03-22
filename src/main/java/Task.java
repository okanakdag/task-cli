import java.time.LocalDateTime;

public class Task {

    private final int id;

    private String description;

    private String status;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // New task constructor
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "New";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Loading constructor
    public Task(int id, String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // doing as setter?
    public void updateStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
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

    public String getStatus() {
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
